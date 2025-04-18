package com.example.core.domain

enum class City(
    val cityName: String,
    val longitude: Double,
    val latitude: Double
) {
    CAIRO("Cairo", 31.2357, 30.0444),
    ALEXANDRIA("Alexandria", 29.9187, 31.2001),
    GIZA("Giza", 31.2109, 30.0131),
    LUXOR("Luxor", 32.6396, 25.6872),
    ASWAN("Aswan", 32.8998, 24.0889),
    HURGHADA("Hurghada", 33.8329, 27.2579),
    SHARM_EL_SHEIKH("Sharm El Sheikh", 34.3299, 27.9158),
    PORT_SAID("Port Said", 32.3019, 31.2653),
    SUEZ("Suez", 32.5498, 29.9737),
    ISMAILIA("Ismailia", 32.2715, 30.5852),
    TANTA("Tanta", 30.9982, 30.7865),
    MANSOURA("Mansoura", 31.3795, 31.0364),
    ZAGAZIG("Zagazig", 31.5020, 30.5877),
    DAMANHUR("Damanhur", 30.4682, 31.0341),
    BANI_SUEF("Bani Suef", 31.0900, 29.0744),
    FAYOUM("Fayoum", 30.8418, 29.3084),
    BENI_MAZAR("Beni Mazar", 30.8012, 28.5041),
    MALLAWI("Mallawi", 30.8467, 27.7314),
    MINYA("Minya", 30.7545, 28.1099),
    ASSIUT("Assiut", 31.1837, 27.1800),
    SOHAG("Sohag", 31.1859, 26.5560),
    QENA("Qena", 32.7181, 26.1551),
    KAFR_EL_SHEIKH("Kafr El Sheikh", 30.9335, 31.1118),
    DAMIETTA("Damietta", 31.8065, 31.4175),
    MIT_GHAMR("Mit Ghamr", 31.2576, 30.7163),
    DESOUK("Desouk", 30.6706, 31.1309),
    ARISH("Arish", 33.7718, 31.1249),
    RAS_GHAREB("Ras Ghareb", 33.1123, 28.3589);

    companion object {
        fun getCityByName(name: String): City? {
            return entries
                .find { it.cityName.equals(name, ignoreCase = true) }
        }
    }
}
