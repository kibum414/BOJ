import java.util.*;

class Solution {

    static Set<Set<String>> answer;
    static String[] user, banned;
    static int bLen;

    public int solution(String[] user_id, String[] banned_id) {

        answer = new HashSet<>();
        user = user_id;
        banned = banned_id;
        bLen = banned.length;

        dfs(new LinkedHashSet<>());

        return answer.size();

    }

    private static void dfs(Set<String> set) {

        if (set.size() == bLen) {
            if (isBannedId(set)) answer.add(new HashSet<>(set));

            return;
        }

        for (String userId : user) {
            if (set.add(userId)) {
                dfs(set);
                set.remove(userId);
            }
        }

    }

    private static boolean isBannedId(Set<String> set) {

        int idx = 0;

        for (String userId : set) {
            String bannedId = banned[idx++];

            if (userId.length() != bannedId.length()) return false;

            for (int i = 0, len = userId.length(); i < len; i++) {
                if (bannedId.charAt(i) == '*') continue;

                if (userId.charAt(i) != bannedId.charAt(i)) return false;
            }
        }

        return true;

    }

}