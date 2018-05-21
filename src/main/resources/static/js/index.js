//Funções javascript para index.html
//Inclui jquery, bottstrap e utils.

function dateFormat(value) {
	var d = new Date(value);
	return d.toLocaleString();
}
function onlyDigits(value) {
	return value.toString().replace(/\D/g, "");
}

function fixedFormat(value) {
	var n = parseFloat(value);
	return n.toFixed(2);
}

function categoriaFormat(value, row, index, field) {
	var id = row['id'];
	var codigoUsu
	if (value == null) {
		var newDirective = '<a onclick="javascript:getDetail(\'' + id + '\')" data-toggle="modal" data-target="#modal">-Categorizar-</a>'

		return newDirective;
	} else {
		return value;
	}

}

function getDetail(id) {
	var location = window.location.href;
	$.ajax({
		url : location + 'findById',
		type : "POST",
		contentType : "application/json; charset=utf-8",
		dataType : 'text',
		data : JSON.stringify({
			"id" : id
		}),
		success : function(data) {
			exibir(data);
		},
		error : function(data) {
			if (data.responseJSON != '') {
				$('#message').text('data.responseJSON');
				$('#divMessage').show();
			}
		},
		timeout : 15000
	});

}
comboTextSelect('#categoria', '#sugestoes')
function exibir(data) {
	var content = JSON.parse(data);
	console.log(content);
	$('#id').val(content['id']);
	$('#data').val(dateFormat(content['data']));
	$('#descricao').val(content['descricao']);
	$('#categoria').val('');
	var dica = content['categoria'];
	if (dica != null) {
		$('#categoria').attr("placeholder", "Melhor dica: Selecione " + dica + " abaixo.");
	}
	$('#divSugestoes').addClass('hidden');
	var sugestoesList = '';
	var autoComplete = [];
	$('#sugestoes').empty();
	$('.typeahead').typeahead('destroy')

	$.each(content['categorias'], function(key, value) {
		var categoria = value['categoria'];
		sugestoesList += '<option value="' + categoria + '">' + categoria + '</option>';
		autoComplete.push(categoria);
	});
	if (sugestoesList != '') {
		$('#divSugestoes').removeClass('hidden');
		$('#sugestoes').append(sugestoesList);
		comboTextSelect('#categoria', '#sugestoes')

	}
	$(".typeahead").typeahead({
		source : autoComplete,
		autoSelect : true
	});
	$('#modal').modal('show')
}

function comboTextSelect(textId, selectId) {
	$(textId).bind('focusout', function() {
		$(selectId).val('');
		$(selectId + ' option[value="' + $(textId).val().trim().toUpperCase() + '"]').prop('selected', true);
	});
	$(selectId).change(function() {
		$(textId).val($(selectId).val()).change()
	});
}