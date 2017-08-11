/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var palestrasCount = 1;
var mesaRedondaActived = false;
var solicitante = false;
var editor = false;
var apoioTecnico = false;
var revisor = false;
$a = jQuery.noConflict();
$a(document).ready(function() {
    

    
    var heightWindow = $a(window).height();
    var heightBody   = document.body.clientHeight;
    if(heightWindow > heightBody){
        $a("#footer").css('position', 'fixed');
        $a("#footer").css('bottom', '0');
    } 
    
    $a("#javax_faces_developmentstage_messages").hide();
    
    $a("#btn-menu-cadastro").click(function(){
        $a("#cadastro-submenu").slideToggle("slow");
    });
    
    $a("#btn-menu-objetos").click(function(){
        $a("#objetos-submenu").slideToggle("slow");
    });

    //Menu Oficios e memorandos
    // create Editor from textarea HTML element with default set of tools
    //Open and hide submenu of oficios/memos
    $a("#btn-menu-documento").click(function(){
        $a("#documento-submenu").slideToggle("slow");
    });
    
      
    //----- Editor -----
    //Text Editor
    

    if($a("#editor1").length)
    {        
        //var editor1 = CKEDITOR.document.getById('editor1');
        //CKEDITOR.config.resize_enabled = false;
        console.log(document.getElementById('editor-container').clientWidth + " " +document.getElementById('editor-container').clientHeight );
        if($a("#form-doc\\:editor-hidden").val() != "")
        {
            //CKEDITOR.instances.editor1.setData("");
            tinymce.get('editor').setContent("");
            var memo_number = $a("#doc-memo-create-number").html();
            var oficio_number = $a("#doc-oficio-create-number").html();
            var current_date = $a("#doc-dt").html();
            
            var element = document.createElement("DIV");
            $a(element).html($a("#form-doc\\:editor-hidden").val());
            $a(element).find("#doc-memo-create-number").html(memo_number);
            $a(element).find("#doc-oficio-create-number").html(oficio_number);
            $a(element).find("#doc-dt").html(current_date);
            tinymce.get('editor').setContent($a(element).html());
           /// CKEDITOR.instances.editor1.setData($a(element).html());            
        }
        else
        {
            if($a( "input:checked" ).val() == '1')
               // CKEDITOR.instances.editor1.setData($a("#memorando-template").html());
                tinymce.get('editor').setContent($a("#memorando-template").html());
            else if($a( "input:checked" ).val() == '2')
                //CKEDITOR.instances.editor1.setData($a("#oficio-template").html());
               tinymce.get('editor').setContent($a("#oficio-template").html());
        }
        
        if($a("#form-doc\\:setores" ) != "" || $a( "#form-doc\\:setores" ) != null)
            fillRecipientField();
    }
    
    //Open input for inserting new recipient into database
    $a("#btn-add-recipient").click(function(){
        $a("#doc-create-create_recipient").toggle("show");
    });
 
    //loading doc templates
    $a("input[value='1']").click(function() {
        // CKEDITOR.instances.editor1.setData($a("#memorando-template").html());
        tinymce.get('editor').setContent($a("#memorando-template").html());
    });
    $a("input[value='2']").click(function() {
        //CKEDITOR.instances.editor1.setData($a("#oficio-template").html());
        tinymce.get('editor').setContent($a("#oficio-template").html());
    });
    
    //Autocomplete for Recipient Name
    
    $a(function() {        
        var availableTags = [];
        $a("#doc-create-destinatarioList").find("li").each(function(){
           availableTags.push($a(this).html()); 
        });
        
        //console.log(availableTags);
        $a( "#form-doc\\:setores" ).autocomplete({
            source: availableTags
        });
    });
    
    //Filling the recipient field in the document body
    function fillRecipientField()
    {
        var recipients = $a(".doc-recipient");
        for(var i =0; i < recipients.length; i++)
           $a(recipients[i]).html("PARA: " + $a("#form-doc\\:setores" ).val());
    }
    
    $a(function() {
        $a( "html" ).on("click", function(){
            fillRecipientField();
        });
    });
//    //Exporting edited document into pdf
//    $a("#btn-create-exportar").click(function(){
//        var editor1 = CKEDITOR.document.getById('editor1');
//        var element = document.createElement("DIV");
//        $a(element).html(CKEDITOR.instances.editor1.getData());
//        $a(element).attr("id", "pdf-export-doc-content");
//        getPDF(".cke_contents_ltr");
//        //exportToPDF(element);
//    });
    //Event click on 'salvar' button on the doc page
    $a("#form-doc\\:btn-salvar_doc").hover(function() {        
        var doc_message = $a("#editor-container");
        $a(doc_message).find(".doc-recipient").attr("class", "doc-recipient-done");
        
        //store id to 'destinatario' inputHidden id
        var currentRecipient = $a( "#form-doc\\:setores" ).val();
        var recipientForDB = currentRecipient.trim();
        
        
        
        $a("#doc-create-destinatarioList").find("li").each(function(){           
           var listRecipient = $a(this).html();
           listRecipient = listRecipient.trim();
           if(recipientForDB.localeCompare(listRecipient) == 0)
               $a( "#form-doc\\:inputHidden-setor" ).val($a(this).attr("id"));
        });
        
       $a("#form-doc\\:editor-hidden").val(tinymce.get('editor').getContent());
    }, function(){});
    
    
    
    function exportToPDF(source)
    {
        var pdf = new jsPDF('p', 'pt');
        //$a(source).html("<html><head></head><body>" + $a(source).html() + "</body></html>");
        var id = $a(source).attr('id');
        var width =  $a(source).width();
        $a(source).css('background', '#fff');
        var margins = {
            top: 30,
            bottom: 30,
            left: 30,
            width:460
        };
        
        var elementHandler = {
            id: function (element, renderer) {
              return true;
            }
        };
        
        pdf.fromHTML(
            source, 
            margins.left,
            margins.top,
            {'width': margins.width, 'elementHandlers': elementHandler,
                'pagesplit': true, "background": "white", "imageQuality": 100,
                'encoding':"WinAnsiEncoding"}, function(){
                pdf.output("dataurlnewwindow");
            }          
        );
    }
    
    function getPDF(selector) {
//        kendo.drawing.drawDOM($a(selector)).then(function(group){
//            group.options.set("pdf", {
//                paperSize: 'A4',
//                multiPage: true
//            });
//          kendo.drawing.pdf.saveAs(group, "document.pdf");
//        });
        
        //$a(selector).kendoGrid({ dataSource: data });

        kendo.drawing.drawDOM( $a(selector), {
          paperSize: "A4",
          multiPage: true,
          margin: "0.5cm"
        }).then(function(group){
          kendo.drawing.pdf.saveAs(group, "grid.pdf");
        });
    }
    
    //Date Picker For Creating Activity Form
    
    $a('.date_picker').datepicker({
        format: 'dd/mm/yyyy'
    });
    
    $a('.date-mask').mask("00r00r0000", {
      translation: {
        'r': {
          pattern: /[\/]/, 
          fallback: '/'
        }, 
        placeholder: "__/__/____"
      }
    });
    
    $a('.time-mask').mask('00r00', {
      translation: {
        'r': {
          pattern: /[:]/, 
          fallback: ':'
        }, 
        placeholder: "__:__"
      }
    });
    
    $a('.num-pos-mask').mask('sr', {
        translation: {
            's':{
              pattern: /[1-9]/  
            },
            'r': {
                pattern: /[0-9]/
            },
            placeholder: "nn"
        }
    });
    
    $a('.ip-mask').mask('0ZZ.0ZZ.0ZZ.0ZZ', {
        translation:  {
            'Z': {
                pattern: /[0-9]/, 
                optional: true
            }
        }
    });
    
    //Masks for Pessoa form
    $a(".form-nome").mask("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr", {
        translation: {
            'r': {
                pattern:/^[A-Za-z]/
            }
        }
        
    });
    
    $a(".form-cellphone").mask("(99) 99999-9999", {
        translation: {
            placeholder: "(99) 99999-9999"
        }
    });
    
    $a(".form-telefone").mask("(99) 9999-9999", {
        translation: {
            placeholder: "(99) 9999-9999"
        }
    });
    
    
    $a(".form-email").change(function(){
        var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        
        if(!re.test($a(this).val()))
        {
            $a(this).focus($a(this).addClass("invalid-input"));
        }
        else
        {
            $a(this).focus($a(this).removeClass("invalid-input"));
        }
    });
    
    $a(".form-cpf").mask("rrrrrrrrrrr", {
        translation: {
            'r': {
                pattern: /[0-9]/
            },
            placeholder: "nn"
        }
    });
    
    //show Pessoa panel

    if(($a(".formPessoaMessage").html() != undefined && !$a(".formPessoaMessage").is(":empty")) 
            || $a("#messagePanelPessoa").html() != undefined && !$a("#messagePanelPessoa").is(":empty"))
    {
        //Showing responsavel light box temporally because pessoa form is a component
        //of responsavel form, and both are hidden-initialized
        showLightBox("create_responsavel");
        showLightBox("list_pessoa");
        showLightBox("create_pessoa");
    }
   
    //Open and close Mesa Redonda Panel depending on the clicks on Modalidade
    //select and Tipo select
    $a( "#formAtividade\\:modalidade" ).on("change",function () {
        var selected = $a(this).find("option:selected").val();
        if(selected.indexOf("Videoconferência") == -1 || selected.indexOf("Webconferência") == -1)
            $a('#formAtividade\\:panel-mesa-redonda').slideUp('fast');
    });
    
    var palestrasStatus = false;
    $a( document.body ).on("click", "#btn-more-subjects", function () {
       
        //var selected = $a(this).find("option:selected").val();
        
        if(palestrasStatus == false){
           $a('#formAtividade\\:panel-mesa-redonda').slideToggle('slow');
           $a("label[for='formAtividade\\:palestrante']").html("Moderador:");
           $a("#btn-more-subjects").removeClass("btn-success fa-plus");
           $a("#btn-more-subjects").addClass("btn-warning fa-remove");
           $a("#formAtividade\\:subjects-control").val(true);
           palestrasStatus = true;
          // alert('desgraça');
        }
        else
        {
           $a('#formAtividade\\:panel-mesa-redonda').slideUp('fast');
           $a("label[for='formAtividade\\:palestrante']").html("Palestrante:");
           $a("#btn-more-subjects").addClass("btn-success fa-plus");
           $a("#btn-more-subjects").removeClass("btn-warning fa-remove");
           $a("#formAtividade\\:subjects-control").val(false);
           palestrasStatus = false;
        }
        
    });
    
    $a("#formAtividade\\:tbr_ckeckbox").click(function (){
        $a('#formAtividade\\:panel-atividade-tbr').slideToggle('slow');
    });

    if($a(".ui-state-active").html() != undefined)
    {
        $a('#formAtividade\\:panel-atividade-tbr').slideToggle('slow');
    }
    
    $a("#formAtividade\\:tipo").on("change", function(){
        console.log("chamando");
        var value = $a("#formAtividade\\:tipo").val();
        alert(value);
    });
    function reloadDataTableAndCalendar()
    {
        //---- Visualizar Documento section
        //adding attributes to the docs list table
        $a("#table-doc-list").attr("data-sort-name", "name");
        $a("#table-doc-list").attr("data-sort-order", "desc");
        $a("#table-doc-list").find("th").attr("data-align", "center");


        //Changing language and disabling general search feature
        var dataTableList = $a("#table-doc-list");
        var table;

        table = $a("#table-doc-list").DataTable({
                    "order":[[0, "desc"]],
                    "pageLength": 10,
                    "autoWidth":false,                    
                    "language": {
                        "lengthMenu": "Mostrar _MENU_ por página",
                        "zeroRecords": "Busca não encontrada",
                        "info": "Mostrando página _PAGE_ de _PAGES_",
                        "infoEmpty": "Dados não cadastrados",
                        "infoFiltered": "(Total de _MAX_ dados)",
                        "search": "Procurar",
                        "previous": "Anterior",
                        "next": "Próximo"
                    }
                });
        $a("#table-doc-list_filter").attr("style", "display:none");
        $a("#table-doc-list_paginate").removeClass("dataTables_paginate");

        //implementing column filtering
        function filterColumn ( i, val ) {
            $a("#table-doc-list").DataTable().column(i).search(
                val,
                false,
                true
            ).draw();
        }

        $a('input.column_filter').on( 'keyup change', function () {
            filterColumn( $a(this).attr('title'),  $a(this).val());
        });

        // --- Opening document for viewing and generating pdf
        $a("#btn-view-exportar").click(function(){
            var source = $a("#doc-view-panel")[0];
            getPDF("#doc-view-panel");
           // exportToPDF(source);
        });
        
        $a.getScript('http://arshaw.com/js/fullcalendar-1.6.4/fullcalendar/fullcalendar.min.js',function(){

        var date = new Date();
        var d = date.getDate();
        var m = date.getMonth();
        var y = date.getFullYear();

        $a('#calendar').fullCalendar({
          header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
          },
          editable: false,
          events: [
            {
              title: 'All Day Event',
              start: new Date(y, m, 1)
            },
            {
              title: 'Long Event',
              start: new Date(y, m, d-5),
              end: new Date(y, m, d-2)
            },
            {
              id: 999,
              title: 'Repeating Event',
              start: new Date(y, m, d-3, 16, 0),
              allDay: false
            },
            {
              id: 999,
              title: 'Repeating Event',
              start: new Date(y, m, d+4, 16, 0),
              allDay: false
            },
            {
              title: 'Meeting',
              start: new Date(y, m, d, 10, 30),
              allDay: false
            },
            {
              title: 'Lunch',
              start: new Date(y, m, d, 12, 0),
              end: new Date(y, m, d, 14, 0),
              allDay: false
            },
            {
              title: 'Birthday Party',
              start: new Date(y, m, d+1, 19, 0),
              end: new Date(y, m, d+1, 22, 30),
              allDay: false
            },
            {
              title: 'Click for Google',
              start: new Date(y, m, 28),
              end: new Date(y, m, 29),
              url: 'http://google.com/'
            }
          ]
        });
      })
    }
    
    
    
    reloadDataTableAndCalendar();
    
    
});

function showLightBoxAtividade()
{
    var value = document.getElementById('formAtividade:tipo').value;
    if(value == "Edição de Vídeo")
        PF('dlg1').show();
}
  

function showLightBox(id) {
    $a("#" + id).fadeIn("slow");
    //document.getElementById(id).style.display = "block";
}

function setMesaRedondaActived(){
    mesaRedondaActived = true;
}

function setSolicitanteTrue(){
    solicitante = true;
}

function setEditorTrue(){
    editor = true;
}

function setApoioTrue(){
    apoioTecnico = true;
}

function setRevisorTrue(){
    revisor = true;
}

function getBiremeValue()
{
    var codigo = document.getElementById('decs-list:current_codigo').value;
    var formAtv = document.getElementById('formAtividade:decs');
    var formCurso = document.getElementById('formsCreateCurso:decs');
    if(formAtv != null)
        formAtv.value = codigo;
    if(formCurso != null)
        formCurso.value = codigo;
}

function getSelectedValue(form){  
    var nome = document.getElementById('form-list-doc:current_pessoa').value;
    var id = nome.split(':')[1];
    nome =nome.split(':')[0];
    var formAtividadeInput = document.getElementById('formAtividade');
    if(solicitante){
        document.getElementById('formAtividade:solicitante-readonly').value = nome;
        document.getElementById('formAtividade:solicitante').value = id;
        solicitante = false;
    }
    else if(editor)
    {
        document.getElementById('formEdicaoVideo:editor-readonly').value = nome;
        document.getElementById('formEdicaoVideo:editor').value = id;
        editor = false;
    }
    else if(apoioTecnico)
    {
        document.getElementById('formEdicaoVideo:apoio-readonly').value = nome;
        document.getElementById('formEdicaoVideo:apoio').value = id;
        apoioTecnico = false;
    }
    else if(revisor)
    {
        document.getElementById('formEdicaoVideo:revisor-readonly').value = nome;
        document.getElementById('formEdicaoVideo:revisor').value = id;
        revisor = false;
    }
    else if(mesaRedondaActived == true)
    {
        document.getElementById('formAtividade:palestrante-readonly' + palestrasCount).value = nome;
        document.getElementById('formAtividade:pessoa' + palestrasCount).value = id;
        mesaRedondaActived = false;
    }
    else
    {
        if(formAtividadeInput != null)
        {
            if(document.getElementById('formAtividade:palestrante-readonly') != null)
            {
                document.getElementById('formAtividade:palestrante-readonly').value = nome;
                document.getElementById('formAtividade:pessoa').value = id;
            }
        }
        if( document.getElementById('formResponsavel') != null)
        {
            if(document.getElementById('formResponsavel:pessoaShowName') != null)
            {
                document.getElementById('formResponsavel:pessoaShowName').value = nome;
                document.getElementById('formResponsavel:pessoa').value = id;
            }
        }
        if( document.getElementById('formArtigo') != null)
        {
            if(document.getElementById('formArtigo:autor1') != null)
            {
                document.getElementById('formArtigo:autor1').value = id; 
                document.getElementById('formArtigo:autor-readonly1').value = nome;
            }
        }
    }
    
    hideLightBox(form);
};

function setInstSelected(){  
    document.getElementById('formResponsavel:instituicaoShowName').value = document.getElementById('formConexao:instituicao_label').innerHTML;
    document.getElementById('formResponsavel:instituicao').value = document.getElementById('formConexao:instituicao_input').value;
};

function hideLightBox(id){
    $a("#" + id).fadeOut("fast");
    //document.getElementById(id).style.display = "none";
};

function validateResposavel(){
    var pessoa = document.formulario.pessoa;
    var instituicao = document.formulario.instituicao;
    var setor = document.formulario.setor;
    var ramal = document.formulario.ramal;
    var formu = document.formulario;
    
    if(pessoa.value=="" || pessoa.value==null){
        alert("Pessoa é um campo obrigatório.");
        pessoa.focus();
    }
    else if(instituicao.value=="" || instituicao.value==null){
        alert("Instituição é um campo obrigatório.");
        instituicao.focus();
    }
    else if(setor.value=="" || setor.value==null){
        alert("Setor é um campo obrigatório.");
        setor.focus();
    }
    else if(ramal.value=="" || ramal.value==null){
        alert("Ramal é um campo obrigatório.");
        ramal.focus();
    }
    else {
        formu.submit();
    }
};

function load(data, str){
    var image = document.getElementById("form:"+str+"Image");
    var output = document.getElementById("form:"+str+"Output");
    var carregando = document.getElementById("form:"+str+"Carregando");
    var btn = document.getElementById("form:"+str+"Btn");
    var insertBtn = document.getElementById("form:"+str+"InsertBtn");
    if(data.status === "begin"){
            image.style.display = "inline";
            output.style.display = "none";
            carregando.style.display = "inline";
            btn.style.display = "none";
            insertBtn.style.display = "none";
    }
    else if(data.status === "success"){
            image.style.display = "none";
            output.style.display = "block";
            carregando.style.display = "none";
            insertBtn.style.display = "block";
    }
};

function loadUbs(data){
    load(data, "ubs");
};

function loadCbo(data){
    load(data, "cbo");
};

function loadMun(data){
    load(data, "mun");
};

function loadEquipe(data){
    load(data, "equipe");
};

function loadPessoa(data){
    load(data, "pessoa");
};

function loadProf(data){
    load(data, "prof");
};

function checkPessoaCadastro(form){
    form = document.getElementById(form);
    alert(form);
    
}

function drawPieChartEvaluations(title, dataArray, question)
{
    
    google.charts.setOnLoadCallback(drawChart);
    console.log(dataArray);
    function drawChart() {
        var data = google.visualization.arrayToDataTable(dataArray);

        var options = {
            is3D: true,
            chartArea: {width: '100%'},
            title: title,
            width: 400,
            height: 400                                  
        };

        var chart = new google.visualization.PieChart(document.getElementById('pichart_values_'+question));
        chart.draw(data, options);
    }
}


function drawBarChart(title, dataArray)
{
    google.charts.load('current', {packages: ['corechart', 'bar']});
    google.charts.setOnLoadCallback(drawBasic);

    function drawBasic() {

        var data = google.visualization.arrayToDataTable(dataArray);
        var view = new google.visualization.DataView(data);
        view.setColumns([0, 1,
        { calc: "stringify",
          sourceColumn: 1,
          type: "string",
          role: "annotation" },
        ]);

        var options = {
            chartArea: {width: '50%'},
            hAxis: {
              title: title,
              textStyle:{
                bold:true,
                italic: false
              },
              minValue: 0
            },
            vAxis: {
              title: '',
              
            },
            width: 900,
            height: 600,
            animation:{
                "startup": true,
                duration: 1000,
                easing: 'out'
            }
        };
        var chart_div = document.getElementById('barchart_values');
        var chart = new google.visualization.BarChart(document.getElementById('barchart_values'));

        chart.draw(view, options);
    }
}

function datePickerLoader()
{
    
    //Date Picker For Creating Activity Form
    
    $a('.date_picker').datepicker({
        format: 'dd/mm/yyyy'
    });
    
    $a('.date-mask').mask("00r00r0000", {
      translation: {
        'r': {
          pattern: /[\/]/, 
          fallback: '/'
        }, 
        placeholder: "__/__/____"
      }
    });
}


function drawColumnChart(title, dataArray)
{
    google.charts.load("current", {packages:['corechart']});
    google.charts.setOnLoadCallback(drawBasic);

    function drawBasic() {

        var data = google.visualization.arrayToDataTable(dataArray);
        
        var view = new google.visualization.DataView(data);
        

        var options = {
            chartArea: {width: '150%', height: '85%'},
            hAxis: {
              title: title,
              textStyle:{
                bold:true,
                italic: false
              },
              
              minValue: 0
            },
            colors: ['#236693', '#3498db', '#02cc8f', '#66ffd0'],
            legend: {
                position: 'top',
                maxLines: 4
            },
            width: 1000,
            height: 600,
            animation:{
                "startup": true,
                duration: 1000,
                easing: 'in'
            },
            annotations: {
                textStyle: {
                    fontName: 'Times-Roman',
                    fontSize: 20,
                    bold: true,
                    italic: false,
                    color: '#000000',  // The color of the text outline.
                    opacity: 0.8          // The transparency of the text.
                }
            }
        };
        
        
        var obj1 = {
              id:"col1",
              calc: "stringify",
              sourceColumn: 1,
              type: "string",
              role: "annotation"
            };
        
        var columns = [0, 1,
            obj1, 2,
            {
              id:"col2",
              calc: "stringify",
              sourceColumn: 2,
              type: "string",
              role: "annotation"
            }, 3,
            {
              id:"col3",
              calc: "stringify",
              sourceColumn: 3,
              type: "string",
              role: "annotation"
            }, 4,
            {
              id:"col4",
              calc: "stringify",
              sourceColumn: 4,
              type: "string",
              role: "annotation"
            }
        ];
        
        view.setColumns(columns);
        
        var chart = new google.visualization.ColumnChart(document.getElementById("barchart_values"));
        
        //var chart = new google.charts.Bar(document.getElementById('barchart_values'));
        //var chart = new google.visualization.ColumnChart(document.getElementById("barchart_values"));

        chart.draw(view, options);
        
        //obj = view.getViewColumns()[2];
        var hideAtv = document.getElementById("hideAtividades");
        
        document.getElementById('hideSeries1').addEventListener('click', hideColumn, false);
        document.getElementById('hideSeries2').addEventListener('click', hideColumn, false);
        document.getElementById('hideSeries3').addEventListener('click', hideColumn, false);
        document.getElementById('hideSeries4').addEventListener('click', hideColumn, false);
        //document.getElementById('resetSeries').addEventListener('click', resetSeries, false);

        function hideColumn(e) {
            var hideColumn = parseInt(e.target.value);
            var viewColumns = [];

            view.getViewColumns().forEach(function (columnDef) {
                if (columnDef.hasOwnProperty('sourceColumn')) {
                    if (columnDef.sourceColumn !== hideColumn) {
                      viewColumns.push(columnDef);
                    }
                } else if (columnDef !== hideColumn) {
                    viewColumns.push(columnDef);
                }
            });
            console.log(hideColumn);
            view.setColumns(viewColumns);
            chart.draw(view, options);
        }

        function resetSeries(e) {
            view.setColumns(columns);
            chart.draw(view, options);
        }
    }
}
