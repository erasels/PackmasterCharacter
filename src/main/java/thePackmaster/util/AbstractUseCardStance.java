package thePackmaster.util;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.stances.AbstractStance;

public abstract class AbstractUseCardStance extends AbstractStance {

    public abstract void onAfterUseCard(UseCardAction action, AbstractCreature target);

}
