package melonystudios.variants.settings;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import melonystudios.variants.Variants;
import melonystudios.variants.util.VSUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class RVSettingsManager {
    public static final int SETTINGS_FILE_VERSION = 8011; // RV 8.0.11
    public static final Logger LOGGER = LogManager.getLogger(Variants.DISPLAY_MOD_ID + "/settings");
    private final File settingsFile = new File("config/melonystudios", Variants.DISPLAY_MOD_ID + ".json");
    private RVSettings settings = null;

    /// Gets the active instance of *Revaried*'s settings.
    public RVSettings settings() {
        return this.settings;
    }

    /// Creates the serializer for *Revaried*'s settings file.
    public GsonBuilder createSettingsSerializer() {
        return new GsonBuilder().registerTypeAdapter(RVSettings.class, new RVSettings.Serializer());
    }

    public void load() {
        if (this.settingsFile.exists()) {
            try {
                Gson gson = this.createSettingsSerializer().create();
                this.settings = gson.fromJson(new String(Files.readAllBytes(this.settingsFile.toPath()), StandardCharsets.UTF_8), RVSettings.class);
                LOGGER.debug(VSUtils.translate("console.variants.settings.loading", "Loaded settings file from %s.", this.settingsFile.getAbsolutePath()));
            } catch (Exception exception) {
                LOGGER.warn(VSUtils.translate("exception.variants.settings_error.loading", "Unable to load the settings file, creating a new one."), exception);
            }
        }
        if (this.settings == null) this.settings = new RVSettings();
        this.upgradeSettings(this.settings);
        this.save();
    }

    public void save() {
        String settings = this.createSettingsSerializer().setPrettyPrinting().create().toJson(this.settings);
        try {
            if (this.settingsFile.getParentFile() != null) this.settingsFile.getParentFile().mkdirs();
            OutputStream stream = Files.newOutputStream(this.settingsFile.toPath());
            Writer writer = new OutputStreamWriter(stream, Charsets.UTF_8.newEncoder());
            writer.write(settings);
            writer.flush();
            writer.close();
            LOGGER.debug(VSUtils.translate("console.variants.settings.saving", "Saved settings file to %s.", this.settingsFile.getAbsolutePath()));
        } catch (IOException exception) {
            LOGGER.warn(VSUtils.translate("exception.variants.settings_error.saving", "Error while saving the settings file!"), exception);
        }
    }

    public void upgradeSettings(RVSettings settings) {
        switch (settings.version) {
            case 1805: {
                settings.version = 1807;
                settings.crimsonWheatPatches = true;
                settings.soulCarrotPatches = true;
                settings.warpedPotatoPatches = true;
                settings.meltingBeetPatches = true;
                break;
            }
            case 1807: {
                settings.version = 1808;
                break;
            }
        }
    }
}
