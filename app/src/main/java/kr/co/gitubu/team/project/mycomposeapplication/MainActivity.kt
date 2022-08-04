package kr.co.gitubu.team.project.mycomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun MyApp(names: List<String> = listOf("World", "Compose")) {
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