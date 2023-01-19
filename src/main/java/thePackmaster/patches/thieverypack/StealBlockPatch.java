package thePackmaster.patches.thieverypack;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import thePackmaster.actions.thieverypack.StealAllBlockAction;

public class StealBlockPatch {
	@SpirePatch2(clz = AbstractCreature.class, method = "renderBlockIconAndValue")
	public static class RenderIconPatch {
		@SpirePrefixPatch
		public static void Prefix(AbstractCreature __instance, @ByRef float[] x, @ByRef float[] y) {
			if (StealAllBlockAction.activatedInstance != null && StealAllBlockAction.affectedCreature == __instance) {
				StealAllBlockAction.activatedInstance.calcPosition(x, y);
			}
		}
	}
}
