class Exercise01 {
    private static final String err = "Invalid input";
    public static void main(String[] args) {
        if (args.length == 3) {
            try {
                double first = Double.parseDouble(args[0]),
                        second = Double.parseDouble(args[2]);
                String operator = args[1];
                switch (operator) {
                    case "+":
                        System.out.println(first + second);
                        break;
                    case "-":
                        System.out.println(first - second);
                        break;
                    case "x":
                        System.out.println(first * second);
                        break;
                    case "/":
                        System.out.println(first / second);
                        break;
                    case "xx":
                        System.out.println(Math.pow(first, second));
                        break;
                    default:
                        System.out.println(err);
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println(err);
            } catch (Exception e) {
                System.out.println(err);
            }
        } else {
            System.out.println(err);
        }
    }
}
