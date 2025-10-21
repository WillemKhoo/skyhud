package com.freereal.data_extractor;

public class ActionBarData {
    public enum Type {
        NORMAL,
        ABILITY,
        UNKNOWN
    }

    final private String actionBarMessage;
    final private Type type;

    public ActionBarData(String actionBarMessage) {
        this.actionBarMessage = actionBarMessage;

        if (actionBarMessage.contains("❤") &&
            actionBarMessage.contains("✎") &&
            actionBarMessage.contains("❈")) {
            this.type = Type.NORMAL;
        }
        else {
            this.type = Type.UNKNOWN;
        }
    }

    public void updateToSkyblockData() {
        switch (type) {
            case Type.NORMAL:
                this.updateFromNormal();
                break;
            case Type.UNKNOWN:
                System.out.println("Unknown actionbar type");
                break;
            default:
                break;
        }
    }

    private void updateFromNormal() {
        try {
            SkyblockData sbd = SkyblockData.getInstance();

            String fullHpString = actionBarMessage.substring(0, actionBarMessage.indexOf("❤"));
            fullHpString = formatActionBarMessage(fullHpString);
            String hpString = fullHpString.substring(0, fullHpString.indexOf("/"));
            String maxHpString = fullHpString.substring(fullHpString.indexOf("/") + 1);

            String fullDefString = actionBarMessage.substring(actionBarMessage.indexOf("❤") + 1, actionBarMessage.indexOf("❈"));
            String defString = formatActionBarMessage(fullDefString);

            String brokenMpString = actionBarMessage.substring(actionBarMessage.indexOf("❈") + 1, actionBarMessage.indexOf("✎"));
            String fullMpString = formatActionBarMessage(brokenMpString);
            String mpString = fullMpString.substring(0, fullMpString.indexOf("/"));
            String maxMpString = fullMpString.substring(fullMpString.indexOf("/") + 1);

            sbd.hp = Integer.parseInt(hpString);
            sbd.maxHp = Integer.parseInt(maxHpString);
            sbd.def = Integer.parseInt(defString);
            sbd.mp = Integer.parseInt(mpString);
            sbd.maxMp = Integer.parseInt(maxMpString);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String formatActionBarMessage(String text) {
        String newText = text;

        final int LAST_SPACE_INDEX = newText.lastIndexOf(" ");

        if (LAST_SPACE_INDEX >= 0) {
            newText = newText.substring(LAST_SPACE_INDEX + 1);
        }

        newText = newText.replaceAll(",", "");

        return newText;
    }
}
