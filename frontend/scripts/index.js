
document.addEventListener('DOMContentLoaded', () => {

    
    // Validamos que el boton buscar este deshabilitado cuando el texto este vacío
    const inputSearch = document.getElementById("input-search")
    const buttonSearch = document.getElementById("button-search")

    const formContainer = document.getElementById("form-container")
    formContainer.style = "display: none"

    inputSearch.addEventListener("keyup", () => {
        //console.log("Tecla presionada")
        buttonSearch.disabled = false
        // Pintamos según el tema
        const body = document.querySelector("body")

    } )

    // Scroll hasta el resultado
    buttonSearch.addEventListener("click", () => {
        scrollToGifs()
        
    })
    const formP = document.getElementById("form-physical")
    const formL = document.getElementById("form-legal")

     // Habilitamos formulario cliente persona fisica
    document.getElementById("button-create-physical").addEventListener('click', () => {
        //const formContainer = document.getElementById("form-container")
        //formContainer.style = "display: none"
        formContainer.style = `display: flex;
                                justify-content: column;
                                flex-direction: column;
                                align-content: space-between;`
        formL.style = "display: none;"
        
        formP.style = `display: flex;
                    justify-content: column;
                    flex-direction: column;
                    align-content: space-between;`
    })

    // Habilitamos formulario cliente persona juridica
    document.getElementById("button-create-legal").addEventListener('click', () => {
        const formContainer = document.getElementById("form-container")
        //formContainer.style = "display: none"
        formContainer.style = `display: flex;
                                justify-content: column;
                                flex-direction: column;
                                align-content: space-between;`
        // desactivamos el componente P
        formP.style = "display: none;"

        formL.style = `display: flex;
                    justify-content: column;
                    flex-direction: column;
                    align-content: space-between;`
    })

    // Funcionalidad boton buscar
    document.getElementById("button-search").addEventListener('click', () => {
        //const container = document.getElementsByClassName("gifs-container")
        const search = document.getElementById("input-search").value
        limitGifs = 12
        const datos = getGifsFromGiphy(search, urlGiphySearch)
        processGifsFromGiphy(datos, limitGifs)
    })

})




