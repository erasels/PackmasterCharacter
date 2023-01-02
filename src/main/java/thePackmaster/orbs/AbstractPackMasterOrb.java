package thePackmaster.orbs;

import basemod.abstracts.CustomOrb;

public abstract class AbstractPackMasterOrb extends CustomOrb {
    public AbstractPackMasterOrb(String ID, String NAME, int basePassiveAmount, int baseEvokeAmount, String passiveDescription, String evokeDescription, String imgPath) {
        super(ID, NAME, basePassiveAmount, baseEvokeAmount, passiveDescription, evokeDescription, imgPath);
    }
    public void PassiveEffect(){

    }
}
