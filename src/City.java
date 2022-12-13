public enum City {
    Værløse,
    Ballerup,
    Aalborg,
    Snekkersten,
    NONE;



    public static City findCity(String s) {
        switch (s.toUpperCase().replace(" ", "")) {
            case "VÆRLØSE":
                return Værløse;
            case "BALLERUP":
                return Ballerup;
            case "AALBORG":
                return Aalborg;
            case "SNEKKERSTEN":
                return Snekkersten;
        }
        return NONE;
    }
}
