package eden.PlotEditor;

public class Plant {
    public enum PlantType {
        ARAGULA("Aragula", 3) /* // Need to implement abstract class for all PlantType values
            @Override
            public Image getImage() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }*/,
        BUSH_BEANS("Bush Beans", 3),
        POLE_BEANS("Pole Beans", 4),
        BEETS("Beets", 3),
        BROCCOLI("Broccoli", 12),
        BRUSSELS_SPROUTS("Brussels Sprouts", 20),
        CABBAGE("Cabbage", 12),
        CARROTS("Carrots", 1),
        CELERY("Celery", 8),
        CUCUMBERS("Cucumbers", 9),
        KALE("Kale", 10),
        KOHLRABI("Kohlrabi", 4),
        LETTUCE("Lettuce", 10),
        OKRA("Okra", 6),
        ONIONS("Onions", 5),
        SNOW_PEAS("Snow Peas", 4),
        PEPPERS("Peppers", 10),
        POTATOES("Potatoes", 10),
        RADISH("Radish", 1),
        SPINACH("Spinach", 4),
        SUMMER_SQUASH("Summer Squash", 18),
        SWISS_CHARD("Swiss Chard", 9),
        TURNIPS("Turnips", 4);
        
        private final int spacing;
        private final String name;
        
        private PlantType(String name, int spacing) {
            this.name = name;
            this.spacing = spacing;
        }
        
        public int getSpacing() {
            return spacing;
        }
        
        public String getName() {
            return name;
        }
        
        // Implement this class for each PlantType
        // Placeholder abstract method
//        public abstract Image getImage();
    }
    
    private final PlantType type;
    private int x, y;
    
    public Plant(PlantType type, int  x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }
    
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public PlantType getPlantType() {
        return type;
    }
    
    public int getSpacing() {
        return type.getSpacing();
    }
    
    public String getTypeName() {
        return type.getName();
    }
    
    public int getXCoordinate() {
        return x;
    }
    
    public int getYCoordinate() {
        return y;
    }
    
    public static PlantType[] getPlantCatalog() {
        return PlantType.values();
    }
}
