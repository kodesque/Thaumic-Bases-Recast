package tbases.util.research;

import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;

import java.util.ArrayList;

public class ResearchUtils {

    private static ArrayList<ResearchEntry> modEntries = new ArrayList<>();

//    @SubscribeEvent
//    public void commandEvent(CommandEvent ce) {
//        if(ce.getCommand() instanceof CommandThaumcraft && ce.getParameters().length > 0 && ce.getParameters()[0].equalsIgnoreCase("reload"))
//        {
//            new Thread(() ->
//            {
//                while(TBResearch.RES_CAT.research.containsKey("TB_FIRST"))
//                    try
//                    {
//                        Thread.sleep(10L);
//                    } catch(InterruptedException e)
//                    {
//                        e.printStackTrace();
//                    }
//
//                DudCategory.initResearch();
//            }).start();
//        }
//    }

//    private static Method addResearchToCategory = null;
//
//    protected static void addResearchToCategory(ResearchEntry ri) {
//        if (addResearchToCategory == null)
//            try
//            {
//                addResearchToCategory = thaumcraft.common.lib.research.ResearchManager.class.getDeclaredMethod("addResearchToCategory", ResearchEntry.class);
//                addResearchToCategory.setAccessible(true);
//            } catch (NoSuchMethodException | SecurityException e) {
//                e.printStackTrace();
//            }
//        try
//        {
//            addResearchToCategory.invoke(null, ri);
//        } catch(Throwable e)
//        {
//            e.printStackTrace();
//        }
//    }

    protected static void addResearch(ResearchEntry entry) {
        modEntries.add(entry);
    }

    protected static void addTextContent(ResearchEntry entry) {
        String key = entry.getKey();
        ResearchStage[] stages = entry.getStages();
        ResearchAddendum[] addenda = entry.getAddenda();

        for (int i = 0; i < stages.length; i++) {
            String textStage = "research." + key + ".text.stage." + i;
            stages[i].setText(textStage);

            if (addenda.length > 0) {
                String textAddenda = "research." + key + ".text.addendum." + i;
                addenda[i].setText(textAddenda);
            }

        }

    }
}
