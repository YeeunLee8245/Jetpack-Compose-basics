package kr.co.gitubu.team.project.mycomposeapplication

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.gitubu.team.project.mycomposeapplication.ui.theme.MyComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {    // 컴포즈 직접 생성
            MyComposeApplicationTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){
    // TODO: This state should be hoisted
    var shouldShowOnboarding by remember { mutableStateOf(true) }   // by로 위임(delegate) => .value 쓸 필요없이 바로 변수명으로 이용 가능
    // 가장 직관적인 OnboardingScreen 컴포저블로 상태를 전달하는 것이지만 깔끔하지 못하다
    if(shouldShowOnboarding){
        OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false})    // 콜백 함수 삽입
    }else
        Greetings()
}

@Composable
fun Greetings(names: List<String> = listOf("World", "Compose")) {
    // A surface container using the 'background' color from the theme
    Surface(color = MaterialTheme.colors.background,
    modifier = Modifier.padding(vertical = 4.dp)) {
        Column() {
            for (name in names){
                Greeting(name)
            }
        }
    }
}

@Composable // 구성 가능한 함수(컴포저블 함수)
fun Greeting(name: String) {
    // remember: 컴포저블이 재구성될 때마다 괄호 안 객체를 재생성&초기화하지 않고 이전 값을 유지하게 함
    // 값이 변하는 객체가 여러 컴포저블에 쓰여야한다면 공통 상위 항목 안, 즉, greeting을 호출하는 함수 안에 있어야한다. => 상태 호이스팅
    val expanded = remember {mutableStateOf(false)} // mutable~: 값이 변경되면 종속성을 재구성하라고 알려주는 역할
    val extraPadding = if(expanded.value) 48.dp else 0.dp   // 계산이 복잡한 경우엔 CPU 사이클을 많이 사용하기 때문에 remember 안에 넣는 것 추천
    Surface(color = MaterialTheme.colors.primary,
    modifier = Modifier.padding(horizontal = 8.dp ,vertical = 4.dp)) {    // horizontal은 좌우(<>이 모습을 수평이라고 생각해서 이렇게 이름 붙인듯), vertical은 위아래 단순 수평, 수직으로 헷갈리지 말자.
        Row(modifier = Modifier.padding(24.dp)) {
            // 내부에 있는 컴포저블이 위, 아래로 배치됨, fillMaxWidth: 상위 컨테이너(surface)의 너비를 사용한다
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello")
                Text(text = "$name")
            }
            // OutlineButton이 아닌 Button으로 쓰면 기본 머티리얼 디자인이 입혀진(보라색) 버튼이 나옴
            OutlinedButton(onClick = { expanded.value = !expanded.value }) {
                Text(if(expanded.value) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit   // 콜백 함수 넘겨받음
) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,   // 수직 가운데 정렬
            horizontalAlignment = Alignment.CenterHorizontally  // 수평 "
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked  // onClick: 버튼이 클릭되면 onClick에 정의된 함수를 호출하고 내부의 명령을 실행
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320, uiMode = UI_MODE_NIGHT_YES) // 나이트모드 미리보기
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    MyComposeApplicationTheme() {
        OnboardingScreen(onContinueClicked = {})    // 미리보기에서는 특별한 동작이 없기 때문에 아무것도 바뀌지 않아야 함
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {  // 앱을 완전 빌드하지않고도 design을 보게 해준다. 이름은 바꿀 수 있어도 매개변수를 받아선 안된다.
    MyComposeApplicationTheme { // 미리보기로 보고싶은 것 여기에 호출
        MyApp()
    }
}

@Composable
private fun MyName(){
    androidx.compose.material.Surface(color = MaterialTheme.colors.background) {
        Greeting(name = "예은")   // 컴포즈의 재사용
        Column {
            val names = arrayListOf<String>("이예은", "옌")
            for (name in names){
                Greeting(name = name)
            }
        }
    }
}