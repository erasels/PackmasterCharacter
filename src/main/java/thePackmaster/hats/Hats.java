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
import com.megacrit.cardcrawl.core.Settings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.util.ImageHelper;
import thePackmaster.util.TexLoader;

import java.util.HashMap;
import java.util.Map;

public class Hats {

    public static String currentHat;

    private static Map<String, AttachPoint> map = new HashMap<>();
    private static float hatHeight;

    public static void removeHat(Skeleton skeleton, String hatID) {
        //Write this if its ever needed some day.
    }

    private static Skeleton playerSkeleton;

    private static Bone headbone;
    private static Slot headslot;
    private static int foundHeadSlot;

    private static RegionAttachment attachment;

    private static void setupSkeleton() {
        AbstractPlayer p = BaseMod.findCharacter(ThePackmaster.Enums.THE_PACKMASTER);
        playerSkeleton = ReflectionHacks.getPrivate(p, AbstractCreature.class, "skeleton");

        String bonename;
        String slotname;

        int headslotIndex = 0;


        Array<Bone> possiblebones = playerSkeleton.getBones();
        for (Bone b : possiblebones) {
            bonename = b.toString().toLowerCase();
            if (bonename.equals("head") || bonename.equals("skull") || bonename.equals("neck_3")) {
                headbone = b;
                break;
            }

        }

        Array<Slot> possibleslots = playerSkeleton.getSlots();
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

    public static void addHat(String hatID, float scaleX, float scaleY, float offsetX, float offsetY, float angle) {
        if (playerSkeleton == null) {
            setupSkeleton();
        }

        String imgPath = getImagePathFromHatID(hatID);

        if (attachment == null) {
            map.put(hatID, new AttachPoint(hatID, headbone.toString(), map.size() + 1, imgPath, scaleX, scaleY, offsetX, offsetY, angle));

            AttachPoint attachPoint = map.get(hatID);

            String attachName = attachPoint.attachName;
            int slotIndex = foundHeadSlot;

            if (attachPoint.attachIndex != null) {
                if (playerSkeleton.findSlotIndex(attachName + attachPoint.attachIndex) < 0) {
                    // Create a new slot for the attachment
                    Slot origSlot = headslot;
                    Slot slotClone = new Slot(new SlotData(origSlot.getData().getIndex(), attachName + attachPoint.attachIndex, origSlot.getBone().getData()), origSlot.getBone());
                    slotClone.getData().setBlendMode(origSlot.getData().getBlendMode());
                    playerSkeleton.getSlots().insert(slotIndex, slotClone);

                    Array<Slot> drawOrder = playerSkeleton.getDrawOrder();
                    drawOrder.add(slotClone);
                    playerSkeleton.setDrawOrder(drawOrder);
                }
                attachName = attachName + attachPoint.attachIndex;
            }

            Texture tex = TexLoader.getTexture(map.get(hatID).imgPath);
            tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            TextureRegion region = new TextureRegion(tex);
            attachment = new RegionAttachment(attachPoint.ID);
            attachment.setRegion(region);
            attachment.setWidth(tex.getWidth());
            attachment.setHeight(tex.getHeight());
            attachment.setX(attachPoint.x * Settings.scale);
            attachment.setY(attachPoint.y * Settings.scale + ((map.size() - 1) * hatHeight));
            attachment.setScaleX(attachPoint.scaleX * Settings.scale);
            attachment.setScaleY(attachPoint.scaleY * Settings.scale);
            attachment.setRotation(attachPoint.angle);
            attachment.updateOffset();

            Skin skin = playerSkeleton.getData().getDefaultSkin();
            skin.addAttachment(slotIndex, attachment.getName(), attachment);

            playerSkeleton.setAttachment(attachName, attachment.getName());

        } else {
            TextureRegion region = ImageHelper.asAtlasRegion(TexLoader.getTexture(imgPath));
            attachment.setRegion(region);
        }
    }

    public static class AttachPoint {
        final String ID;
        final String attachName;
        final Integer attachIndex;
        final String imgPath;
        final float scaleX;
        final float scaleY;
        final float x;
        final float y;
        final float angle;

        public AttachPoint(String id, String attachName, String img, float scaleX, float scaleY, float x, float y, float angle) {
            this(id, attachName, null, img, scaleX, scaleY, x, y, angle);
        }

        public AttachPoint(String id, String attachName, Integer attachIndex, String img, float scaleX, float scaleY, float x, float y, float angle) {
            ID = id;
            this.attachName = attachName;
            this.attachIndex = attachIndex;
            imgPath = img;
            this.scaleX = scaleX;
            this.scaleY = scaleY;
            this.x = x;
            this.y = y;
            this.angle = angle;
        }
    }


    public static String getImagePathFromHatID(String hatID) {
        return SpireAnniversary5Mod.modID + "Resources/images/hats/" + hatID.replace(SpireAnniversary5Mod.modID + ":", "") + "Hat.png";
    }

    public static void atRunStart() {
        //TODO: Add the current hat to the player
    }
}