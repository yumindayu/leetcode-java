/*
 *  力扣中文版本 第1001题（注意：点亮一盏灯，是会把八个方向照亮，而不是把八个方向的灯也点亮）
 * 
 */
class Solution {
    //定义八个方向坐标
    private static final int[][] DIRS = new int[][]{{0, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    //定义横 竖 撇 捺 四个方向被照亮的次数，这里只需要一个行数为key存入map即可，不需要把每个坐标点都做记录，因为要被照亮都是一整行一整列被照亮
    private Map<Integer, Integer> rowCnts, colCnts, lrCnts, rlCnts;
    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        long ln = (long)n;
        rowCnts = new HashMap<>();
        colCnts = new HashMap<>();
        lrCnts = new HashMap<>();
        rlCnts = new HashMap<>();
        //定义被点亮的坐标，因为有重复的所以需要去重，用set来实现，坐标要经过2维转1维 公司 x 坐标 乘以N 加 y坐标
        Set<Long> points = new HashSet<>();
        //循环点亮的坐标，记录在points中，并照亮八个方向的点
        for(int[] lamp: lamps) {
            int x = lamp[0], y = lamp[1];
            long p = ln * x + y;
            if(!points.contains(p)) {
                points.add(p);//记录点亮的点
                operate(x, y, 1); //照亮八个方向，因为是被照明，所以次数加1，
            }
        }
        int[] ans = new int[queries.length];
        for(int i = 0; i < queries.length; i++) {
            int x = queries[i][0], y = queries[i][1];
            //判断要查询的点是否是被照明状态
            if(rowCnts.getOrDefault(x, 0) > 0 || colCnts.getOrDefault(y, 0) > 0 || lrCnts.getOrDefault(x + y, 0) > 0 || rlCnts.getOrDefault(x - y, 0) > 0){
                ans[i] = 1;
                //如果该点是照明状态，需要把矩阵内亮着的灯关闭，以该查询点为坐标，查询八个方向中的坐标点，是否在points中存在，如果存在即移除
                //同时把该点亮的点的坐标 横竖撇捺范围内被照亮的坐标点次数减1
                for(int[] dir: DIRS){
                    int nx = x + dir[0], ny = y + dir[1];
                    if(nx >= 0 && ny >= 0 && nx < n && ny < n){
                        long p = ln * nx + ny;
                        if(points.contains(p)){
                            points.remove(p);
                            operate(nx, ny, -1);
                        }
                    }
                }
            }
        }
        return ans;
    }

    private void operate(Integer x, Integer y, int diff) {
        add(rowCnts, x, diff);
        add(colCnts, y, diff);
        add(lrCnts, x + y, diff);
        add(rlCnts, x - y, diff);
    }

    private void add(Map<Integer,Integer> map, Integer key, int val) {
        int cur = map.getOrDefault(key, 0);
        map.put(key, cur + val);
    }
}