package thePackmaster.hats;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.*;
import com.esotericsoftware.spine.attachments.RegionAttachment;
import com.megacrit.cardcrawl.core.Settings;
import thePackmaster.util.TexLoader;

import java.util.HashMap;
import java.util.Map;

public class Hats {

    private static Map<String, AttachPoint> map = new HashMap<>();
    private static float hatHeight;

    public static void removeHat(Skeleton skeleton, String hatID) {
        //Write this if its ever needed some day.
    }

    public static boolean addHat(Skeleton skeleton, String hatID, String imgPath, float scaleX, float scaleY, float offsetX, float offsetY, float angle) {
        Bone headbone = null;
        Slot headslot = null;
        String bonename;
        String slotname;

        int headslotIndex = 0;


        Array<Bone> possiblebones = skeleton.getBones();
        for (Bone b : possiblebones) {
            bonename = b.toString().toLowerCase();
            if (bonename.equals("head") ||
                    bonename.equals("skull") ||
                    bonename.equals("neck_3")
            ) {
                headbone = b;
                break;
            }

        }

        Array<Slot> possibleslots = skeleton.getSlots();
        for (Slot s : possibleslots) {
            slotname = s.getBone().toString().toLowerCase();
            if (slotname.equals("packmaster_head")) {
                headslot = s;
                break;
            }
            headslotIndex++;
        }

        map.put(hatID,
                new AttachPoint(
                        hatID,
                        headbone.toString(),
                        map.size() + 1,
                        imgPath,
                        scaleX, scaleY,
                        offsetX, offsetY,
                        angle
                ));

        AttachPoint attachPoint = map.get(hatID);

        String attachName = attachPoint.attachName;
        int slotIndex = headslotIndex;

        if (attachPoint.attachIndex != null) {
            if (skeleton.findSlotIndex(attachName + attachPoint.attachIndex) < 0) {
                // Create a new slot for the attachment
                Slot origSlot = headslot;
                Slot slotClone = new Slot(new SlotData(origSlot.getData().getIndex(), attachName + attachPoint.attachIndex, origSlot.getBone().getData()), origSlot.getBone());
                slotClone.getData().setBlendMode(origSlot.getData().getBlendMode());
                skeleton.getSlots().insert(slotIndex, slotClone);

                Array<Slot> drawOrder = skeleton.getDrawOrder();
                drawOrder.add(slotClone);
                skeleton.setDrawOrder(drawOrder);
            }
            attachName = attachName + attachPoint.attachIndex;
        }

        Texture tex = TexLoader.getTexture(map.get(hatID).imgPath);
        tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        TextureRegion region = new TextureRegion(tex);
        RegionAttachment attachment = new RegionAttachment(attachPoint.ID);
        attachment.setRegion(region);
        attachment.setWidth(tex.getWidth());
        attachment.setHeight(tex.getHeight());
        attachment.setX(attachPoint.x * Settings.scale);
        attachment.setY(attachPoint.y * Settings.scale + ((map.size() - 1) * hatHeight));
        attachment.setScaleX(attachPoint.scaleX * Settings.scale);
        attachment.setScaleY(attachPoint.scaleY * Settings.scale);
        attachment.setRotation(attachPoint.angle);
        attachment.updateOffset();

        Skin skin = skeleton.getData().getDefaultSkin();
        skin.addAttachment(slotIndex, attachment.getName(), attachment);

        skeleton.setAttachment(attachName, attachment.getName());
        return true;
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

        public AttachPoint(
                String id, String attachName, String img,
                float scaleX, float scaleY,
                float x, float y,
                float angle
        ) {
            this(id, attachName, null, img, scaleX, scaleY, x, y, angle);
        }

        public AttachPoint(
                String id, String attachName, Integer attachIndex, String img,
                float scaleX, float scaleY,
                float x, float y,
                float angle
        ) {
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
}