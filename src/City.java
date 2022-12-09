public enum City {
    Værløse,
    Ballerup,
    Frederiksberg,
    Aalborg,
    Helsingør,
    NONE;



    public static City findCity(String s) {
        switch (s.toUpperCase().replace(" ", "")) {
            case "VÆRLØSE":
                return Værløse;
            case "BALLERUP":
                return Ballerup;
            case "FREDERIKSBERG":
                return Frederiksberg;
            case "AALBORG":
                return Aalborg;
            case "HELSINGØR":
                return Helsingør;
        }
        return NONE;
    }
}
