package jrv1000.base_finder.config;

import com.google.gson.Gson;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.impl.builders.DropdownMenuBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.Arrays;
import java.util.List;

public class Config {

    static int rangeX = 0;
    static int rangeY = 0;

    static String biome = "";

    static String currentStructure = "";

    static List<String> structures;

    static int seed = 0;

    static int distanceX;
    static int distanceY;

    public static int getRangeX(){return rangeX;}
    public static int getRangeY(){return rangeY;}

    public static String getBiome(){return biome;}

    public static String getStructure(){return currentStructure;}

    public static int getSeed(){return seed;}

    public static int getDistanceX(){return distanceX;}
    public static int getDistanceY(){return distanceY;}

    public static ConfigBuilder MakeConfig(){
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(MinecraftClient.getInstance().currentScreen)
                .setTitle(Text.translatable("config.jrv1000.title"));

        ConfigCategory general = builder.getOrCreateCategory(Text.translatable("config.jrv1000.general_category"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startIntField(Text.translatable("config.jrv1000.general_category.seed"), Config.seed).setSaveConsumer(newValue -> Config.seed = newValue).build());

        general.addEntry(entryBuilder.startIntField(Text.translatable("config.jrv1000.general_category.rangex"), Config.rangeX).setSaveConsumer(newValue -> Config.rangeX = newValue).build()); //x
        general.addEntry(entryBuilder.startIntField(Text.translatable("config.jrv1000.general_category.rangey"), Config.rangeY).setSaveConsumer(newValue -> Config.rangeY = newValue).build()); //y

        general.addEntry(entryBuilder.startIntField(Text.translatable("config.jrv1000.general_category.distancex"), Config.distanceX).setSaveConsumer(newValue -> Config.distanceX = newValue).build()); //x
        general.addEntry(entryBuilder.startIntField(Text.translatable("config.jrv1000.general_category.distancey"), Config.distanceY).setSaveConsumer(newValue -> Config.distanceY = newValue).build()); //y

        general.addEntry(entryBuilder.startStrField(Text.translatable("config.jrv1000.general_category.biomes"), Config.biome)
                .setDefaultValue("")
                .setSaveConsumer(newValue -> Config.biome = newValue).build());//biome

        general.addEntry(entryBuilder.startTextDescription(Text.of("Biome List:\ndesert\nplains\nsvanna\nocean\njungle\ndark oak\nswamp")).build());

        //general.addEntry(entryBuilder. (Text.translatable("config.jrv1000.general_category.biomes"), Config.biome).setSaveConsumer(newValue -> Config.biome = newValue).build()); //structures

        return builder;
    }

    public static class SerializedConfig{

        public int rangeX;
        public int rangeY;

        public String biome;

        public List<String> structures;

        public SerializedConfig(){
            this.rangeX = Config.rangeX;
            this.rangeY = Config.rangeY;

            this.biome = Config.biome;

            this.structures = Config.structures;
        }

        public String serialized(){
            return new Gson().toJson(this);
        }

    }

}