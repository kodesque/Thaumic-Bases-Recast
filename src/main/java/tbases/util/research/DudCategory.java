package tbases.util.research;

import org.apache.commons.lang3.tuple.Triple;
import tbases.init.TBResearch;
import thaumcraft.api.research.ResearchEntry;

public class DudCategory {

    @SuppressWarnings("unchecked")
    protected static void initResearch() {

        new BookEntry()
                .start("TB_FIRST",
                        0 , 0 ,
                        new ResearchIcon(ResearchIcon.getNative("bases")))
                .setMeta(ResearchEntry.EnumResearchMeta.SPIKY)
                .setStages(new BookStage()
                                .start()
                                .setKnowledge(Triple.of(KnowledgeType.OBSERVATION , TBResearch.CAT_NAME , 1))
                                .write(),
                        new BookStage().addSimple())
                .setParents("FIRSTSTEPS")
                .write();

    }



}
