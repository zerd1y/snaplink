public class ShardingSqlPrinter {

    // --- 1. 基础建表语句模板 ---
    // %d 将被替换为 0 到 15 的数字
    private static final String CREATE_TABLE_TEMPLATE =
            "CREATE TABLE IF NOT EXISTS `t_user_%d` (" +
            "  `id` bigint NOT NULL AUTO_INCREMENT," +
            "  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL," +
            "  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL," +
            "  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL," +
            "  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL," +
            "  `mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL," +
            "  `create_time` datetime NULL DEFAULT NULL," +
            "  `update_time` datetime NULL DEFAULT NULL," +
            "  `delete_time` datetime NULL DEFAULT NULL," +
            "  `del_flag` tinyint(1) NULL DEFAULT NULL," +
            "  PRIMARY KEY (`id`) USING BTREE" +
            ") ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;\n"; // 末尾添加换行

    // --- 2. 分片数量配置 ---
    private static final int SHARDING_COUNT = 16;
    private static final String TABLE_NAME_PREFIX = "t_user_";

    public static void main(String[] args) {

        System.out.println("--- 批量建表语句生成开始 (t_user_0 to t_user_15) ---");

        // 使用 StringBuilder 收集所有 SQL 语句，以确保一次性输出整洁
        StringBuilder sqlScript = new StringBuilder();

        for (int i = 0; i < SHARDING_COUNT; i++) {
            // 格式化生成具体的 CREATE TABLE SQL
            String sql = String.format(CREATE_TABLE_TEMPLATE, i);
            sqlScript.append(sql);
        }

        // 打印所有生成的 SQL 语句
        System.out.println(sqlScript.toString());

        System.out.println("--- 批量建表语句生成结束 ---");
    }
}