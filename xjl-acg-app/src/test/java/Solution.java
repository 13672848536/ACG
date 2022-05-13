public class Solution {
    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;
//        for( int i = 0; i<2;i++){
//            System.out.println(twoSum(nums,target)[i]);
//        }
        System.out.println(isPalindrome(20));
    }
    public static int[] twoSum(int[] nums, int target) {
        int[] num = new int[2];
        for( int i = 0; i < nums.length;i++){
            for(int j = 0; j < i ;j++){
                int k = nums[i] +nums[j];
                if(k == target){
                    num[0] = j;
                    num[1] = i;
                    return num;
                }
            }
        }
        return num;
    }
    public static boolean isPalindrome(int x) {
        if(x <0){
            return false;
        }
        String y=String.valueOf(x);
        int k = y.length()-1;
        if(y.length()%2==1){
            for(int i =0; i <= k/2;i++){
                if(y.charAt(i) != y.charAt(y.length()-1-i) ){
                    return false;
                }
            }
        } else {
            for(int i =0; i <= y.length()/2-1;i++){
                if(y.charAt(i) != y.charAt(y.length()-1-i) ){
                    return false;
                }
            }
        }
        return true;
    }
}
