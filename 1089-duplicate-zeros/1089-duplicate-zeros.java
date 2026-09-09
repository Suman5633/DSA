class Solution {
    public void duplicateZeros(int[] arr) {
       int possibleZeros = 0;
       int length = arr.length - 1;
       for (int i = 0; i <= length - possibleZeros; i++) {
        if (arr[i] == 0) {
            if (i == length - possibleZeros) {
                arr[length] = 0;
                length -= 1;
                break;
            }
            possibleZeros++;
        }
       }
       int last = length - possibleZeros;
       for (int i = last; i >= 0; i--) {
        if (arr[i] == 0) {
            arr[i + possibleZeros] = 0;
            possibleZeros--;
            arr[i + possibleZeros] = 0;
        } else {
            arr[i + possibleZeros] = arr[i];
        }
       }
    }   
}