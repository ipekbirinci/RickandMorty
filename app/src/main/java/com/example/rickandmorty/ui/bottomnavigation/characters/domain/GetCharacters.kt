import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import com.example.rickandmorty.base.BaseViewModel
import com.example.rickandmorty.ui.bottomnavigation.characters.data.repo.CharactersRepository
import com.example.rickandmorty.ui.bottomnavigation.characters.data.response.CharactersResponse
import com.example.rickandmorty.ui.bottomnavigation.characters.presantation.CharactersViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GetCharacters @Inject constructor(
    private val repository: CharactersRepository,
) : MutableLiveData<CharactersResponse>(){
    private val mainThreadHandler: Handler = Handler(Looper.getMainLooper())
    val handlerScheduler = Schedulers.from { command -> mainThreadHandler.post(command) }

    @SuppressLint("CheckResult")
    fun execute(charactersViewModel: BaseViewModel, Unit: Unit): MutableLiveData<List<CharactersResponse>> {
        return MutableLiveData<List<CharactersResponse>>().apply {
            charactersViewModel.loading
                repository.getCharacters().subscribeOn(
                    Schedulers.io()
                ).observeOn(handlerScheduler).subscribe({
                    value = it
                }, {

                })

        }
    }
}
