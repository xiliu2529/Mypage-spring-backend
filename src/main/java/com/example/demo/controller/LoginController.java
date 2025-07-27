@PostMapping("/login")
public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> loginInfo) {
    String userid = loginInfo.get("userid");  // 这里改成 userid
    String password = loginInfo.get("password");

    Optional<User> optionalUser = userRepository.findByUserid(userid);

    if (optionalUser.isPresent()) {
        User user = optionalUser.get();
        if (user.getPassword().equals(password)) {
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "登录成功"
            ));
        }
    }

    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
            "success", false,
            "message", "账号或密码错误"
    ));
}
