package me.vitikc.awdamagemod.draw;

import me.vitikc.awdamagemod.config.AWValuesManager;

import java.util.HashMap;
import java.util.Map;

public enum  AWEnumMessages {
    // Messages enum for passing to pipeline only id not whole message

    NONE("", 0, AWValuesManager.TEXT_COLOR),
    EVADED(AWValuesManager.EVADED, 1, AWValuesManager.EVADED_COLOR),
    PARRIED(AWValuesManager.PARRIED, 2, AWValuesManager.PARRIED_COLOR),
    CRITICAL(AWValuesManager.CRITICAL,3, AWValuesManager.CRITICAL_COLOR);
    private String message;
    public int color;
    public int id;
    AWEnumMessages(String message, int id, int color){
        this.message = message;
        this.id = id;
        this.color = color;
    }

    private static Map<Integer, AWEnumMessages> map = new HashMap<Integer, AWEnumMessages>();

    static {
        for (AWEnumMessages msg : AWEnumMessages.values()) {
            map.put(msg.id, msg);
        }
    }


    public static AWEnumMessages valueOf(int id) {
        return map.get(id);
    }

    @Override
    public String toString() {
        return this.message;
    }
}
