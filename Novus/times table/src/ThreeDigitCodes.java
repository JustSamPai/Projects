public class ThreeDigitCodes {
    public static void main(String[] args) {
        //counter for the number of unique digits
        int count = 0;
        for(int i = 1; i <= 4; i++)
        {
            for(int j = 1; j <= 4; j++)
            {
                for(int k = 1; k <= 4; k++)
                {
                    //condition to check if any values are the same as eachother
                    if(i != j && j != k && k != i){
                        // creates a number where i is in the hunderes j in tens and k in ones
                        int num = (i * 100) + (j * 10) + k;
                        System.out.println(num);
                        //update counter
                        count ++;
                    }
                }
            }
        }
        System.out.println("there are the following number of unique digits" + count);
    }
}
