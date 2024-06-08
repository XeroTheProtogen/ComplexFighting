package keno.net.complex_fighting;

import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComplexFighting implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("complex_fighting");
	public static final String MOD_ID = "complex_fighting";

	@Override
	public void onInitialize() {
	}

	public static Identifier modLoc(String loc) {
		return new Identifier(MOD_ID, loc);
	}
}