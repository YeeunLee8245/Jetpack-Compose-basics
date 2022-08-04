package kr.co.gitubu.team.project.mycomposeapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.gitubu.team.project.mycomposeapplication.ui.theme.MyComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {    // 컴포즈 직접 생성
            MyComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable // 구성 가능한 함수(컴포저블 함수)
fun Greeting(name: String) {
    // 기본 테마가 머티리얼 테마로 적용됨
    androidx.compose.material.Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = "안녕하세요")
            Text(text = "안녕!")
        }
        //Text(text = "Hello $name!", modifier = Modifier.padding(24.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeApplicationTheme {
        Greeting("Android")
    }
}

@Composable
private fun MyApp(){
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