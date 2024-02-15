public class CrosswordPuzzle {
    public static void display(char[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j <arr.length; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void solution(char[][] arr, String[] words,  int vidx){
        if(vidx == words.length ){
            display(arr);
            return;
        }
        String word =  words[vidx];
        // display(arr);
        // System.out.println();
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr.length; j++){
                if(arr[i][j] == '-' || arr[i][j] == word.charAt(0)){

                    if(canPlaceWordVertically(arr, word, i, j)){
                        boolean[] wePlaced = placeWordVertically(arr, word, i, j);
                        solution(arr, words, vidx + 1);
                        unPlaceWordVertically(arr, wePlaced, i, j);
                    }

                    if(canPlaceWordHorizontally(arr, word, i, j)){
                        boolean[] wePlaced = placeWordHorizontally(arr, word, i, j);
                        solution(arr, words, vidx + 1);
                        unPlaceWordHorizontally(arr, wePlaced, i, j);
                    }
                }
            }
        }
    }
    public static boolean canPlaceWordHorizontally(char[][] arr, String word, int i, int j){
        if(j - 1 >= 0 && arr[i][j - 1] != '+'){  // ->  +----
            return false;
        }else if(j + word.length() < arr[0].length && arr[i][j + word.length()]!= '+'){ // + --- + <-
            return false;
        }
        for(int jj = 0; jj < word.length(); jj++){
            if(jj + j >= arr.length){  //word ka size larger than horizontal length trivantanampuram
                return false;
            }
            if(arr[i][j + jj] == '-' || arr[i][j + jj] == word.charAt(jj)){ // ya to space ho ya tho uska vo character ho
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
    public static boolean canPlaceWordVertically(char[][] arr, String word, int i, int j){
        if(i - 1 >= 0 && arr[i - 1][j] != '+'){
            return false;
        }else if(i + word.length() < arr[0].length && arr[i + word.length()][j]!= '+'){
            return false;
        }
        for(int ii = 0; ii < word.length(); ii++){
            if(ii + i >= arr.length){
                return false;
            }
            if(arr[i + ii][j] == '-' || arr[i + ii][j] == word.charAt(ii)){
                continue;
            }else{
                return false;
            }
        }
        return true;
    }
    public static boolean[] placeWordHorizontally(char[][] arr, String word, int i, int j){
        boolean[] wePlaced = new boolean[word.length()];
        for(int jj = 0; jj < word.length(); jj++){ 
            if(arr[i][j + jj] == '-'){ // place sirf unme he karange jisme space hai
                arr[i][j + jj] = word.charAt(jj);
                wePlaced[jj] = true;
            }
        }
        return wePlaced;
    }
    public static boolean[] placeWordVertically(char[][] arr, String word, int i, int j){
        boolean[] wePlaced = new boolean[word.length()];
        for(int ii = 0; ii < word.length(); ii++){
            if(arr[i + ii][j] == '-'){
                arr[i + ii][j] = word.charAt(ii);
                wePlaced[ii] = true;
            }
        }
        return wePlaced;
    }
    public static void unPlaceWordHorizontally(char[][] arr, boolean[] wePlaced, int i, int j){
        for(int jj = 0; jj < wePlaced.length; jj++){
            if(wePlaced[jj] == true){ // unplace sirf unko he karange jo apne kiye hai 
                arr[i][j + jj] = '-';
            }
        }
    }
    public static void unPlaceWordVertically(char[][] arr, boolean[] wePlaced, int i, int j){
        for(int ii = 0; ii < wePlaced.length; ii++){
            if(wePlaced[ii] == true){
                arr[i + ii][j] = '-';
            }
        }
    }
    public static void main(String[] args) {
        char[][] arr = {{'+', '-', '+'},
                        {'-', '-', '-'},
                        {'+', '-', '+'}};
        String[] words = {"and", "ant"};
        int vidx = 0;
        solution(arr, words, vidx);
    }
}
