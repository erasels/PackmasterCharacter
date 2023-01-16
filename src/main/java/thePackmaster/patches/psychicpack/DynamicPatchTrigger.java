package thePackmaster.patches.psychicpack;

import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.NotFoundException;
import org.clapper.util.classutil.ClassFinder;
import thePackmaster.patches.psychicpack.occult.OccultPatch;

import java.io.File;
import java.net.URISyntaxException;

@SpirePatch(
        clz = CardCrawlGame.class,
        method = SpirePatch.CONSTRUCTOR
)
public class DynamicPatchTrigger {
    public static void Raw(CtBehavior ctBehavior) throws NotFoundException {
        System.out.println("Starting dynamic patches.");

        ClassFinder finder = new ClassFinder();

        finder.add(new File(Loader.STS_JAR));

        for (ModInfo modInfo : Loader.MODINFOS) {
            if (modInfo.jarURL != null) {
                try {
                    finder.add(new File(modInfo.jarURL.toURI()));
                } catch (URISyntaxException e) {
                    // do nothing
                }
            }
        }

        ClassPool pool = ctBehavior.getDeclaringClass().getClassPool();
        OccultPatch.patch(finder, pool);

        System.out.println("Dynamic patches complete.");
    }
}