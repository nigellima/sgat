/**
 * @license Copyright (c) 2003-2015, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
        
        config.toolbar = [
            {name: "clipboard", items: ['Cut', 'Copy', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo', 'Print']},
            {name: "links", items: ['Link', 'Unlink', 'Anchor']},
            {name: "insert", items: ['Table', 'HorizontalRule', 'SpecialChar', 'Image']},
            {name: "basicstyles", items: ['Bold', 'Italic', 'Strike', '-', 'RemoveFormat']},
            {name: "paragraph", items: ['NumberedList', 'Bulleted', 'Strike', '-', 'Outdent', 'Ident', '-', 'Blockquote',
                 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock']},
            {name: "styles", items: ['Styles', 'Format', 'TextColor']},
            {name: 'document', groups: [ 'mode', 'document', 'doctools' ], items: [ 'Source', '-', 'Save', 'NewPage', 'Preview', 'Print', '-']}
        ];
        config.width = 595;
        config.height = 842;
        config.allowedContent = true;
        config.autoParagraph = false;
};
