package shared.shared.utils;

import shared.definitions.HexType;
import shared.definitions.ResourceType;

public class CatanUtils {

    public static ResourceType toResourceType(HexType type) {
        if(type == HexType.WATER || type == HexType.DESERT) {
            return null;
        }
        else {
            return ResourceType.valueOf(type.toString());
        }
    }
}
