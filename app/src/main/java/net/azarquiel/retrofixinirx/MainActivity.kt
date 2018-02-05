package net.azarquiel.retrofixinirx

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.azarquiel.retrofixinirx.adapters.CustomAdapterPokemon
import net.azarquiel.retrofixinirx.api.PokemonApiService
import net.azarquiel.retrofixinirx.api.PokemonApiServiceDrive
import net.azarquiel.retrofixinirx.model.Tema
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import net.azarquiel.retrofixinirx.model.Result

class MainActivity : AppCompatActivity() {
    private lateinit var pokemons: List<Result>

    // lazy => singleton
    private val pokemonApiService by lazy {
        PokemonApiService.create()
    }
    private val pokemonApiServiceDrive by lazy {
        PokemonApiServiceDrive.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.visibility = VISIBLE
        loadJSON()
        addPokemonDrive(Tema(1,"paco"))
    }

    private fun addPokemonDrive(tema: Tema) {
        pokemonApiServiceDrive.savePokemon("prueba fran5")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { tema ->
                            Toast.makeText(this,"insertado pokemon...",Toast.LENGTH_SHORT).show()
                        },
                        { error ->
                            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                            Log.e("**Paco**",error.message)
                        }
                )


    }

    // con RX
    private fun loadJSON() {
        pokemonApiService.getData("802")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { pokemon ->
                            pokemons = pokemon.results
                            verPokemon()
                            progressBar.visibility = GONE
                        },
                        { error ->
                            progressBar.visibility = GONE
                            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show() }
                )
    }
    private fun verPokemon() {
        rvPokemons.layoutManager  = LinearLayoutManager(this) as RecyclerView.LayoutManager?
        rvPokemons.adapter = CustomAdapterPokemon(this,R.layout.row, pokemons)

    }
}
