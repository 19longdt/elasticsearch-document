import {Component, EventEmitter, Input, Output, SimpleChanges} from '@angular/core';
import {ChangeEvent, CKEditorModule} from '@ckeditor/ckeditor5-angular';

import {
  AccessibilityHelp,
  Alignment,
  Autoformat,
  AutoImage,
  AutoLink,
  Autosave,
  BalloonToolbar,
  BlockQuote,
  Bold,
  ClassicEditor,
  CloudServices,
  Code,
  CodeBlock,
  Essentials,
  FindAndReplace,
  FontBackgroundColor,
  FontColor,
  FontFamily,
  FontSize,
  GeneralHtmlSupport,
  Heading,
  Highlight,
  HorizontalLine,
  HtmlComment,
  HtmlEmbed,
  ImageBlock,
  ImageCaption,
  ImageInline,
  ImageInsert,
  ImageInsertViaUrl,
  ImageResize,
  ImageStyle,
  ImageTextAlternative,
  ImageToolbar,
  ImageUpload,
  Indent,
  IndentBlock,
  Italic,
  Link,
  LinkImage,
  List,
  ListProperties,
  MediaEmbed,
  Mention,
  PageBreak,
  Paragraph,
  PasteFromMarkdownExperimental,
  PasteFromOffice,
  RemoveFormat,
  SelectAll,
  ShowBlocks,
  SimpleUploadAdapter,
  SpecialCharacters,
  SpecialCharactersArrows,
  SpecialCharactersCurrency,
  SpecialCharactersEssentials,
  SpecialCharactersLatin,
  SpecialCharactersMathematical,
  SpecialCharactersText,
  Strikethrough,
  Subscript,
  Superscript,
  Table,
  TableCaption,
  TableCellProperties,
  TableColumnResize,
  TableProperties,
  TableToolbar,
  TextPartLanguage,
  TextTransformation,
  TodoList,
  Underline,
  Undo,
  Base64UploadAdapter
} from 'ckeditor5';
import {FormsModule} from "@angular/forms";
import {DocumentModel} from "../../model/DocumentModel";
import {CommonModule, JsonPipe, NgIf} from "@angular/common";
import {HomeService} from "../../home/home.service";


// import translations from 'ckeditor5/translations/vi.js';
@Component({
  selector: 'jhi-ckeditor5',
  standalone: true,
  imports: [
    CKEditorModule,
    FormsModule,
    JsonPipe,
    NgIf,
    CommonModule
  ],
  templateUrl: './ckeditor5.component.html',
  styleUrl: './ckeditor5.component.scss'
})
export default class Ckeditor5Component {
  @Output() contentOut = new EventEmitter<string>();

  public Editor = ClassicEditor;
  title = 'angular';
  public config = {
    // minimap: {
    //   // container: this.editorMinimap.nativeElement,
    //   // extraClasses: 'editor-container_include-minimap ck-minimap__iframe-content'
    // },
    menuBar: {
      isVisible: true
    },
    toolbar: {
      items: [
        'undo',
        'redo',
        '|',
        'showBlocks',
        '|',
        'heading',
        '|',
        'fontSize',
        'fontFamily',
        'fontColor',
        'fontBackgroundColor',
        '|',
        'bold',
        'italic',
        'underline',
        '|',
        'link',
        'insertImage',
        'insertTable',
        'highlight',
        'blockQuote',
        'codeBlock',
        '|',
        'alignment',
        '|',
        'bulletedList',
        'numberedList',
        'todoList',
        'outdent',
        'indent'
      ],
      // shouldNotGroupWhenFull: false
    },
    plugins: [
      Base64UploadAdapter,
      AccessibilityHelp,
      Alignment,
      Autoformat,
      AutoImage,
      AutoLink,
      Autosave,
      BalloonToolbar,
      BlockQuote,
      Bold,
      CloudServices,
      Code,
      CodeBlock,
      Essentials,
      FindAndReplace,
      FontBackgroundColor,
      FontColor,
      FontFamily,
      FontSize,
      GeneralHtmlSupport,
      Heading,
      Highlight,
      HorizontalLine,
      HtmlComment,
      HtmlEmbed,
      ImageBlock,
      ImageCaption,
      ImageInline,
      ImageInsert,
      ImageInsertViaUrl,
      ImageResize,
      ImageStyle,
      ImageTextAlternative,
      ImageToolbar,
      ImageUpload,
      Indent,
      IndentBlock,
      Italic,
      Link,
      LinkImage,
      List,
      ListProperties,
      // Markdown,
      MediaEmbed,
      Mention,
      // Minimap,
      PageBreak,
      Paragraph,
      PasteFromMarkdownExperimental,
      PasteFromOffice,
      RemoveFormat,
      SelectAll,
      ShowBlocks,
      SimpleUploadAdapter,
      SpecialCharacters,
      SpecialCharactersArrows,
      SpecialCharactersCurrency,
      SpecialCharactersEssentials,
      SpecialCharactersLatin,
      SpecialCharactersMathematical,
      SpecialCharactersText,
      Strikethrough,
      Subscript,
      Superscript,
      Table,
      TableCaption,
      TableCellProperties,
      TableColumnResize,
      TableProperties,
      TableToolbar,
      TextPartLanguage,
      TextTransformation,
      // Title,
      TodoList,
      Underline,
      Undo
    ],
    // balloonToolbar: ['bold', 'italic', '|', 'link', 'insertImage', '|', 'bulletedList', 'numberedList'],
    // fontFamily: {
    //   // supportAllValues: true
    // },
    // fontSize: {
    //   options: [10, 12, 14, 'default', 18, 20, 22],
    //   // supportAllValues: true
    // },
    // heading: {
    //   options: [
    //     {
    //       model: 'paragraph',
    //       title: 'Paragraph',
    //       class: 'ck-heading_paragraph'
    //     },
    //     {
    //       model: 'heading1',
    //       view: 'h1',
    //       title: 'Heading 1',
    //       class: 'ck-heading_heading1'
    //     },
    //     {
    //       model: 'heading2',
    //       view: 'h2',
    //       title: 'Heading 2',
    //       class: 'ck-heading_heading2'
    //     },
    //     {
    //       model: 'heading3',
    //       view: 'h3',
    //       title: 'Heading 3',
    //       class: 'ck-heading_heading3'
    //     },
    //     {
    //       model: 'heading4',
    //       view: 'h4',
    //       title: 'Heading 4',
    //       class: 'ck-heading_heading4'
    //     },
    //     {
    //       model: 'heading5',
    //       view: 'h5',
    //       title: 'Heading 5',
    //       class: 'ck-heading_heading5'
    //     },
    //     {
    //       model: 'heading6',
    //       view: 'h6',
    //       title: 'Heading 6',
    //       class: 'ck-heading_heading6'
    //     }
    //   ]
    // },
    // htmlSupport: {
    //   allow: [
    //     {
    //       name: /^.*$/,
    //       styles: true,
    //       attributes: true,
    //       classes: true
    //     }
    //   ]
    // },
    image: {
      toolbar: [
        'toggleImageCaption',
        'imageTextAlternative',
        '|',
        'imageStyle:inline',
        'imageStyle:wrapText',
        'imageStyle:breakText',
        '|',
        'resizeImage'
      ]
    },
    // initialData:
    //   '<h2>Congratulations on setting up CKEditor 5! 🎉</h2>\n<p>\n    You\'ve successfully created a CKEditor 5 project. This powerful text editor will enhance your application, enabling rich text editing\n    capabilities that are customizable and easy to use.\n</p>\n<h3>What\'s next?</h3>\n<ol>\n    <li>\n        <strong>Integrate into your app</strong>: time to bring the editing into your application. Take the code you created and add to your\n        application.\n    </li>\n    <li>\n        <strong>Explore features:</strong> Experiment with different plugins and toolbar options to discover what works best for your needs.\n    </li>\n    <li>\n        <strong>Customize your editor:</strong> Tailor the editor\'s configuration to match your application\'s style and requirements. Or even\n        write your plugin!\n    </li>\n</ol>\n<p>\n    Keep experimenting, and don\'t hesitate to push the boundaries of what you can achieve with CKEditor 5. Your feedback is invaluable to us\n    as we strive to improve and evolve. Happy editing!\n</p>\n<h3>Helpful resources</h3>\n<ul>\n    <li>📝 <a href="https://orders.ckeditor.com/trial/premium-features">Trial sign up</a>,</li>\n    <li>📕 <a href="https://ckeditor.com/docs/ckeditor5/latest/installation/index.html">Documentation</a>,</li>\n    <li>⭐️ <a href="https://github.com/ckeditor/ckeditor5">GitHub</a> (star us if you can!),</li>\n    <li>🏠 <a href="https://ckeditor.com">CKEditor Homepage</a>,</li>\n    <li>🧑‍💻 <a href="https://ckeditor.com/ckeditor-5/demo/">CKEditor 5 Demos</a>,</li>\n</ul>\n<h3>Need help?</h3>\n<p>\n    See this text, but the editor is not starting up? Check the browser\'s console for clues and guidance. It may be related to an incorrect\n    license key if you use premium features or another feature-related requirement. If you cannot make it work, file a GitHub issue, and we\n    will help as soon as possible!\n</p>\n',
    language: 'vi',
    // link: {
    //   addTargetToExternalLinks: true,
    //   defaultProtocol: 'https://',
    //   decorators: {
    //     toggleDownloadable: {
    //       mode: 'manual',
    //       label: 'Downloadable',
    //       attributes: {
    //         download: 'file'
    //       }
    //     }
    //   }
    // },
    // list: {
    //   properties: {
    //     styles: true,
    //     startIndex: true,
    //     reversed: true
    //   }
    // },
    // mention: {
    //   feeds: [
    //     {
    //       marker: '@',
    //       feed: [
    //         /* See: https://ckeditor.com/docs/ckeditor5/latest/features/mentions.html */
    //       ]
    //     }
    //   ]
    // },
    // menuBar: {
    //   isVisible: true
    // },
    // // minimap: {
    // //   container: this.editorMinimap.nativeElement,
    // //   extraClasses: 'editor-container_include-minimap ck-minimap__iframe-content'
    // // },
    // placeholder: 'Type or paste your content here!',
    // table: {
    //   contentToolbar: ['tableColumn', 'tableRow', 'mergeTableCells', 'tableProperties', 'tableCellProperties']
    // },
    // translations: [translations],
    simpleUpload: {
      uploadUrl: "http://10.100.170.56:8080/api/ckeditor/upload/image",
      onUploadComplete: (response: any) => {
        console.log(response);
      },
    },
  };
  @Input() documentSelected!: DocumentModel;


  editorContent = '';
  contentHtml: any;
  output: any;
  editorInstance: any;
  inputHighLightClass = '';
  messageError = '';

  constructor(private homeService: HomeService) {
  }

  onInputChange(): void {
    if (this.documentSelected.title) {
      this.messageError = '';
    }
  }

  public onContentChange({editor}: ChangeEvent): void {
    this.contentHtml = editor.getData();
    if (this.contentHtml) {
      this.messageError = '';
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    // documentSelected đã thay đổi, bật chế độ readonly cho editor
    if (this.editorInstance && this.documentSelected.id && !this.documentSelected.isEditing) {
      this.editorInstance.enableReadOnlyMode('readonly-id');
    } else {
      this.editorInstance.disableReadOnlyMode('readonly-id');
    }
  }

  // Lưu instance của editor khi sẵn sàng
  public onReady(editor: any): void {
    this.editorInstance = editor;
  }

  onSaveData(): void {
    this.documentSelected.content = this.contentHtml;
    let mess = '';
    if (!this.documentSelected.title) {
      mess += 'title';
    }
    if (!this.documentSelected.content) {
      mess += ', content';
    }

    if (mess !== '') {
      this.inputHighLightClass = 'input-highlight-error';
      this.messageError = 'opps ' + mess + ' empty!';
      setTimeout(() => {
        this.inputHighLightClass = '';
      }, 700);
      return;
    }
    this.homeService.save(this.documentSelected).subscribe(
      {
        next: (result) => {
          window.alert('Success');
          this.editorInstance.disableReadOnlyMode('readonly-id');
          this.documentSelected = new DocumentModel();
        },
        error: (error) => {
          console.dir(error); // Use arrow function
        },
      }
    )
  }

  onEditData(): void {
    if (this.documentSelected.id) {
      this.documentSelected.isEditing = !this.documentSelected.isEditing;
      if (!this.documentSelected.isEditing) {
        this.editorInstance.enableReadOnlyMode('readonly-id');
      } else {
        this.inputHighLightClass = 'input-highlight';
        setTimeout(() => {
          this.inputHighLightClass = '';
        }, 700);
        this.editorInstance.disableReadOnlyMode('readonly-id');
      }
    } else if (this.documentSelected.isEditing) {
      this.editorInstance.disableReadOnlyMode('readonly-id');
    }
  }
}


