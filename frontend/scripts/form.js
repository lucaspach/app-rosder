document.addEventListener('DOMContentLoaded', async () => {
    var form = document.getElementById("form-physical")
    form.onsubmit = async function (e) {
      
      e.preventDefault();
      
      var data = {}
      for (var i = 0, ii = form.length; i < ii; ++i) {
        var input = form[i]
        if (input.name) {
          data[input.name] = input.value
          //console.log(input.name + input.value)
        }
      }

      const options = {
          method: 'POST',
          body: JSON.stringify(data),
          mode: 'no-cors',
          headers: {
            'Content-Type': 'application/json'
          }
      }

      /*const json = {
        "rut": 101010,
        "firstName": "Lucas",
        "lastName": "Pacheco",
        "cc": 165183
        }*/


      try {
        const res = await fetch(URLAPICUSTOMER, options )
        const data = await res.json()
        // Validamos si esta OK para subirlo
        if (res.status === 200) {
          return data
        }
      } catch (error) {
        console.log(error)
      }


   //}


})

