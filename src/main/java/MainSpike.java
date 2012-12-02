public class MainSpike {
    public static void main(String ... args) {
        if(args.length != 1) {
            throw new IllegalArgumentException();
        }
        if(args[0].contains("d")) {
            System.out.println("Starting GUI, all distinct solutions ...");
            Runner.INSTANCE.generateDistinct();
        } else if(args[0].contains("u")) {
            System.out.println("Starting GUI, all unique solutions ...");
            Runner.INSTANCE.generateUnique();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
