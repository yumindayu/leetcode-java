/**
 * 
 * 
 * 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击 和 防御 。给你一个二维整数数组 properties ，其中 properties[i] = [attacki, defensei] 表示游戏中第 i 个角色的属性。

    如果存在一个其他角色的攻击和防御等级 都严格高于 该角色的攻击和防御等级，则认为该角色为 弱角色 。更正式地，如果认为角色 i 弱于 存在的另一个角色 j ，那么 attackj > attacki 且 defensej > defensei 。

    返回 弱角色 的数量。

     

    示例 1：

    输入：properties = [[5,5],[6,3],[3,6]]
    输出：0
    解释：不存在攻击和防御都严格高于其他角色的角色。


    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/the-number-of-weak-characters-in-the-game
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

class Solution {
    public int numberOfWeakCharacters(int[][] properties) {
        Arrays.sort(properties, (o1, o2) -> {
            return o1[0] == o2[0] ? (o1[1] - o2[1]) : (o2[0] - o1[0]);
        });
        int defense = properties[0][1];
        int ret = 0;
        for (int i = 1; i < properties.length; i++) {
            if (properties[i][1] < defense) {
                ret++;
            }
            defense = Math.max(defense, properties[i][1]);
        }
        return ret;
    }
}