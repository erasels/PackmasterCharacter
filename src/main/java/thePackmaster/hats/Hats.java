package thePackmaster.hats;

import basemod.BaseMod;
import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.*;
import com.esotericsoftware.spine.attachments.RegionAttachment;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.util.ImageHelper;
import thePackmaster.util.TexLoader;

public class Hats {

    public static String currentHat;

    public static void removeHat() {

    }

    private static Skeleton skeleton;

    private static Bone headbone;
    private static Slot headslot;
    private static int foundHeadSlot;

    private static RegionAttachment attachment;

    private static void setupMenuSkeleton() {
        AbstractPlayer p = BaseMod.findCharacter(ThePackmaster.Enums.THE_PACKMASTER);
        skeleton = ReflectionHacks.getPrivate(p, AbstractCreature.class, "skeleton");

        String bonename;
        String slotname;

        int headslotIndex = 0;


        Array<Bone> possiblebones = skeleton.getBones();
        for (Bone b : possiblebones) {
            bonename = b.toString().toLowerCase();
            if (bonename.equals("head") || bonename.equals("skull") || bonename.equals("neck_3")) {
                headbone = b;
                break;
            }

        }

        Array<Slot> possibleslots = skeleton.getSlots();
        for (Slot s : possibleslots) {
            slotname = s.getBone().toString().toLowerCase();
            if (slotname.equals("head")) {
                headslot = s;
                break;
            }
            headslotIndex++;
        }

        foundHeadSlot = headslotIndex;
    }

    public static void addHat(boolean inRun, String hatID, float scaleX, float scaleY, float offsetX, float offsetY, float angle) {
        if (inRun) {

        } else {
            if (skeleton == null) {
                setupMenuSkeleton();
            }

            String imgPath = getImagePathFromHatID(hatID);

            if (attachment == null) {
                String attachName = headbone.toString();
                int slotIndex = foundHeadSlot;

                // Create a new slot for the attachment
                Slot origSlot = headslot;
                Slot slotClone = new Slot(new SlotData(origSlot.getData().getIndex(), attachName, origSlot.getBone().getData()), origSlot.getBone());
                slotClone.getData().setBlendMode(origSlot.getData().getBlendMode());
                skeleton.getSlots().insert(slotIndex, slotClone);

                Array<Slot> drawOrder = skeleton.getDrawOrder();
                drawOrder.add(slotClone);
                skeleton.setDrawOrder(drawOrder);

                Texture tex = TexLoader.getTexture(imgPath);
                tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                TextureRegion region = new TextureRegion(tex);
                attachment = new RegionAttachment(hatID);
                attachment.setRegion(region);
                attachment.setWidth(tex.getWidth());
                attachment.setHeight(tex.getHeight());
                attachment.updateOffset();

                Skin skin = skeleton.getData().getDefaultSkin();
                skin.addAttachment(slotIndex, attachment.getName(), attachment);

                skeleton.setAttachment(attachName, attachment.getName());

            } else {
                TextureRegion region = ImageHelper.asAtlasRegion(TexLoader.getTexture(imgPath));
                attachment.setRegion(region);
            }
        }
    }

    public static String getImagePathFromHatID(String hatID) {
        return SpireAnniversary5Mod.modID + "Resources/images/hats/" + hatID.replace(SpireAnniversary5Mod.modID + ":", "") + "Hat.png";
    }

    public static void atRunStart() {
        if (currentHat != null) {
            addHat(true, currentHat, 1, 1, 0, 0, 0);
        }
    }
}