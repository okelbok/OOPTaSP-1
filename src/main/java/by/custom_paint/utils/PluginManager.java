package by.custom_paint.utils;

import by.custom_paint.models.lists.PluginsList;
import by.custom_paint.models.utils.Plugin;

abstract class PluginManager {
    private PluginsList pluginsList;

    abstract public Plugin getPluginFormFile(String path);

    abstract public Plugin addPlugin(String path);
}
