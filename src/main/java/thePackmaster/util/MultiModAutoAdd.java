package thePackmaster.util;

import basemod.AutoAdd;
import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import org.clapper.util.classutil.ClassFinder;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class MultiModAutoAdd extends AutoAdd {

    public MultiModAutoAdd(String... modIDs) {
        super(modIDs[0]);
        if(modIDs.length > 1) {
            ClassFinder _finder = ReflectionHacks.getPrivate(this, AutoAdd.class, "finder");
            Set<String> modIdSet = Arrays.stream(modIDs)
                    .skip(1)
                    .collect(Collectors.toSet());

            for (ModInfo info : Loader.MODINFOS) {
                if (info != null && modIdSet.contains(info.ID) && info.jarURL != null) {
                    try {
                        _finder.add(new File(info.jarURL.toURI()));
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
