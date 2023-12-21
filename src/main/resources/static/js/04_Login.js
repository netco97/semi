function google() {
	console.log("test")
	location.href = "https://accounts.google.com/o/oauth2/auth?client_id=26008134971-89tlqsmj1eja60kktmbitrblda2g069r.apps.googleusercontent.com&redirect_uri=http://localhost:8080/login/oauth2/code/google&response_type=code&scope=https://www.googleapis.com/auth/userinfo.email https://www.googleapis.com/auth/userinfo.profile"
}
function kakao(){
	location.href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=1b89531bb1e1fe47a72f3bf99f4fd2c8&redirect_uri=http://localhost:8080/login/kakao"
	
}