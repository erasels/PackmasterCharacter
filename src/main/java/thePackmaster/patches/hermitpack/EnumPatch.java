package thePackmaster.patches.hermitpack;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class EnumPatch {
    @SpireEnum
    public static AbstractGameAction.AttackEffect HERMIT_GUN;
    @SpireEnum
    public static AbstractGameAction.AttackEffect HERMIT_GUN2;
    @SpireEnum
    public static AbstractGameAction.AttackEffect HERMIT_GUN3;
}
