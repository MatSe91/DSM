package dhbw.karlsruhe.dsm.core.level;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializer;
import com.badlogic.gdx.utils.JsonValue;

public class LevelSerializer implements Serializer<Level> {

	@Override
	public void write(Json json, Level object, Class knownType) {
		json.writeObjectStart();
		json.writeValue("name", object.getName());
		json.writeValue("index", object.getIndex());
		json.writeValue("speed", object.speed);
		json.writeValue("groundHeight", object.getGroundHeight());
		json.writeValue("availablePatterns", object.getAvailablePatterns());
		json.writeObjectEnd();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Level read(Json json, JsonValue jsonData, Class type) {
		Level level = new Level();
		level.setName(jsonData.child().name());
		level.setIndex(jsonData.child().asInt());
		level.speed = jsonData.child().asFloat();
		level.setGroundHeight(jsonData.child().asFloat());
		level.setAvailablePatterns(json.readValue("availablePatterns", ArrayList.class, jsonData));
		return level;
	}

}