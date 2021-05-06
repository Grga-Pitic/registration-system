var forms = document.getElementsByTagName('form');

for (var i = 0; i < forms.length; i++) {
    var form = forms[i];

    form.addEventListener('submit', function () {
        var allInputs = form.getElementsByTagName('input');

        for (var j = 0; j < allInputs.length; j++) {
            var input = allInputs[j];

            if (input.name && !input.value) {
                input.name = '';
            }
        }
    });
}
