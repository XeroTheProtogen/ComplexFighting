package keno.net.complex_fighting;

import keno.net.complex_fighting.events.common.PostureUpdateCallback;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.WardenEntity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.HashMap;

public class ComplexFighting implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("complex_fighting");
	public static final String MOD_ID = "complex_fighting";
	public static HashMap<Class<? extends Entity>, Integer> POSTURE_DATA = new HashMap<>();

	@Override
	public void onInitialize() {
		registerEvents();
		ComplexFighting.addToData(WardenEntity.class, 1000);
	}

	public static <E extends Entity> void addToData(Class<E> entity, int posture) {
		if (!POSTURE_DATA.containsKey(entity)) {
			POSTURE_DATA.put(entity, posture);
		} else {
			throw new KeyAlreadyExistsException(entity.getSimpleName() + " is already mapped to ComplexFighting's posture data");
		}
	}

	private void registerEvents() {
		AttackEntityCallback.EVENT.register(new PostureUpdateCallback());
	}

	public static Identifier modLoc(String loc) {
		return new Identifier(MOD_ID, loc);
	}
}