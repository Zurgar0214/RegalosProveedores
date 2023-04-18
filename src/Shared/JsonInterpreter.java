package Shared;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Clase encargada de a partir de un string convertir a un JSONObject o JSONArray
 */
public class JsonInterpreter {
    private String content;
    public JsonInterpreter(String content) {
        this.content = content;
    }
    private JSONObject convertStringToJSONObject() {
        return new JSONObject(this.content);
    }
    public JSONArray convertStringToJSONArray() {
        return new JSONArray(this.content);
    }
}
