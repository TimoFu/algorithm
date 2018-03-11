

public class Test {
    public static void main(String[] args){
        Test t = new Test();

        System.out.println(t.evenSpace("a bc def g", 17));
    }

    public String evenSpace(String input, int width){
        //TODO validation

        String[] array = input.split(" ");
        int totalCharacterLen = input.length() - (array.length - 1);
        int totalSpaceLen = width - totalCharacterLen;
        int eachInterval = totalSpaceLen / (array.length - 1);
        int remainInterval = totalSpaceLen % (array.length - 1);

        StringBuilder sb = new StringBuilder();
        for (int index = 0; index < array.length; index ++){
            sb.append(array[index]);
            if (index == array.length - 1) break;
            for (int i = 0; i < eachInterval; i ++){
                sb.append("#");
            }
            if (remainInterval > 0){
                sb.append("#");
                remainInterval --;
            }
        }

        return sb.toString();

    }



}
