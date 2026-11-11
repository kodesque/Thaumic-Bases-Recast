package tbases.util.research;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public final class ResearchSerializer {

    private static final String PATH = "src/main/resources/assets/thaumicbases/research/researchtb.json";

    public static void generateFile(List<ResearchEntry> entries) {
        JsonObject root = new JsonObject();
        JsonArray research = new JsonArray();

        for (ResearchEntry entry : entries) {
            research.add(ResearchSerializer.serialize(entry));
        }

        root.add("entries", research);

        try {
            File file = new File(PATH);
            File parent = file.getParentFile();

            if (!parent.exists() && !parent.mkdirs()) {
                throw new IOException();
            }

            File temp = new File(parent, file.getName() + ".tmp");

            try (Writer writer = new OutputStreamWriter(
                    Files.newOutputStream(temp.toPath()), StandardCharsets.UTF_8)) {
                new GsonBuilder()
                        .setPrettyPrinting()
                        .create()
                        .toJson(root, writer);
            }

            if (file.exists() && !file.delete()) {
                throw new IOException("");
            }

            if (!temp.renameTo(file)) {
                throw new IOException();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonObject serialize(ResearchEntry entry) {
        JsonObject json = new JsonObject();

        json.addProperty("key", entry.getKey());
        json.addProperty("name", entry.getName());
        json.addProperty("category", entry.getCategory());

        addStrings(json, "parents", entry.getParents());
        addStrings(json, "siblings", entry.getSiblings());

        JsonArray location = new JsonArray();
        location.add(entry.getDisplayColumn());
        location.add(entry.getDisplayRow());
        json.add("location", location);

        addIcons(json, entry.getIcons());
        addMeta(json, entry.getMeta());

        addItemStacks(json, "reward_item", entry.getRewardItem());
        addKnowledge(json, "reward_knowledge", entry.getRewardKnow());

        json.add("stages", serializeStages(entry));
        addAddenda(json, entry.getAddenda());

        return json;
    }

    private static JsonArray serializeStages(ResearchEntry entry) {
        JsonArray result = new JsonArray();

        if (entry.getStages() == null) {
            return result;
        }

        for (ResearchStage stage : entry.getStages()) {
            JsonObject json = new JsonObject();

            if (stage.getText() != null) {
                json.addProperty("text", stage.getText());
            }

            addResources(json, "recipes", stage.getRecipes());

            addObjects(json, "required_item", stage.getObtain());
            addObjects(json, "required_craft", stage.getCraft());

            addKnowledge(json, "required_knowledge", stage.getKnow());

            addResearchRequirements(
                    json,
                    stage.getResearch(),
                    stage.getResearchIcon()
            );

            if (stage.getWarp() != 0) {
                json.addProperty("warp", stage.getWarp());
            }

            result.add(json);
        }

        return result;
    }

    private static void addIcons(JsonObject json, Object[] icons) {
        if (icons == null || icons.length == 0) {
            return;
        }

        JsonArray array = new JsonArray();

        for (Object icon : icons) {
            if (icon instanceof ItemStack) {
                array.add(serializeItemStack((ItemStack) icon));
            } else if (icon instanceof ResourceLocation) {
                array.add(icon.toString());
            } else if (icon instanceof String) {
                array.add((String) icon);
            }
        }

        json.add("icons", array);
    }

    private static void addMeta(
            JsonObject json,
            ResearchEntry.EnumResearchMeta[] meta
    ) {
        if (meta == null || meta.length == 0) {
            return;
        }

        JsonArray array = new JsonArray();

        for (ResearchEntry.EnumResearchMeta value : meta) {
            array.add(value.name());
        }

        json.add("meta", array);
    }

    private static void addAddenda(
            JsonObject json,
            ResearchAddendum[] addenda
    ) {
        if (addenda == null || addenda.length == 0) {
            return;
        }

        JsonArray array = new JsonArray();

        for (ResearchAddendum addendum : addenda) {
            JsonObject object = new JsonObject();

            if (addendum.getText() != null) {
                object.addProperty("text", addendum.getText());
            }

            addResources(object, "recipes", addendum.getRecipes());
            addStrings(object, "required_research", addendum.getResearch());

            array.add(object);
        }

        json.add("addenda", array);
    }

    private static void addKnowledge(
            JsonObject json,
            String name,
            ResearchStage.Knowledge[] knowledge
    ) {
        if (knowledge == null || knowledge.length == 0) {
            return;
        }

        JsonArray array = new JsonArray();

        for (ResearchStage.Knowledge value : knowledge) {
            if (value == null || value.type == null) {
                continue;
            }

            StringBuilder text = new StringBuilder();
            text.append(value.type.name());

            if (value.category != null) {
                text.append(";").append(value.category.key);
            }

            text.append(";").append(value.amount);

            array.add(text.toString());
        }

        if (array.size() > 0) {
            json.add(name, array);
        }
    }

    private static void addResearchRequirements(
            JsonObject json,
            String[] research,
            String[] researchIcons
    ) {
        if (research == null || research.length == 0) {
            return;
        }

        JsonArray array = new JsonArray();

        for (int i = 0; i < research.length; i++) {
            String key = research[i];

            if (key == null) {
                continue;
            }

            if (researchIcons != null
                    && i < researchIcons.length
                    && researchIcons[i] != null
                    && !researchIcons[i].isEmpty()) {

                key += ";" + researchIcons[i];
            }

            array.add(key);
        }

        if (array.size() > 0) {
            json.add("required_research", array);
        }
    }

    private static void addObjects(
            JsonObject json,
            String name,
            Object[] objects
    ) {
        if (objects == null || objects.length == 0) {
            return;
        }

        JsonArray array = new JsonArray();

        for (Object object : objects) {
            if (object instanceof ItemStack) {
                array.add(serializeItemStack((ItemStack) object));
            } else if (object instanceof String) {
                array.add("oredict:" + object);
            }
        }

        if (array.size() > 0) {
            json.add(name, array);
        }
    }

    private static void addItemStacks(
            JsonObject json,
            String name,
            ItemStack[] stacks
    ) {
        if (stacks == null || stacks.length == 0) {
            return;
        }

        JsonArray array = new JsonArray();

        for (ItemStack stack : stacks) {
            if (stack != null && !stack.isEmpty()) {
                array.add(serializeItemStack(stack));
            }
        }

        if (array.size() > 0) {
            json.add(name, array);
        }
    }

    private static String serializeItemStack(ItemStack stack) {
        if (stack == null || stack.isEmpty()) {
            throw new IllegalArgumentException();
        }

        ResourceLocation registryName = stack.getItem().getRegistryName();

        if (registryName == null) {
            throw new IllegalArgumentException();
        }

        StringBuilder result = new StringBuilder();

        result.append(registryName);
        result.append(";").append(stack.getCount());
        result.append(";").append(stack.getMetadata());

        if (stack.hasTagCompound()) {
            result.append(";").append(stack.getTagCompound().toString());
        }

        return result.toString();
    }

    private static void addResources(
            JsonObject json,
            String name,
            ResourceLocation[] resources
    ) {
        if (resources == null || resources.length == 0) {
            return;
        }

        JsonArray array = new JsonArray();

        for (ResourceLocation resource : resources) {
            if (resource != null) {
                array.add(resource.toString());
            }
        }

        if (array.size() > 0) {
            json.add(name, array);
        }
    }

    private static void addStrings(
            JsonObject json,
            String name,
            String[] values
    ) {
        if (values == null || values.length == 0) {
            return;
        }

        JsonArray array = new JsonArray();

        for (String value : values) {
            if (value != null) {
                array.add(value);
            }
        }

        if (array.size() > 0) {
            json.add(name, array);
        }
    }

}
