import {Component, inject, OnDestroy, OnInit, signal, TemplateRef} from '@angular/core';
import {Router, RouterModule} from '@angular/router';
import {debounceTime, Subject, switchMap} from 'rxjs';
import {takeUntil} from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import {AccountService} from 'app/core/auth/account.service';
import {Account} from 'app/core/auth/account.model';
import {CKEditorModule} from '@ckeditor/ckeditor5-angular';
import * as eee from 'ckeditor5';
import {HomeService} from "./home.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {DocumentModel} from "../model/DocumentModel";
import {SafeHtmlPipe} from "../shared/directive/pipe-html";
import {ElasticSearchItem} from "../model/ElasticSearchItem";

// import { SlashCommand } from 'ckeditor5-premium-features';
// import * as ClassicEditorBuild from '@ckeditor/ckeditor5-build-classic';
import {BrowserModule, DomSanitizer, SafeHtml} from '@angular/platform-browser';
import {OrderBySpecificKeyPipe} from "../shared/directive/order-by-key-pipe";
import {ToastModule} from "primeng/toast";
import {MessageService} from "primeng/api";
import {InputGroupModule} from 'primeng/inputgroup';
import {InputGroupAddonModule} from 'primeng/inputgroupaddon';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {ButtonModule} from "primeng/button";
import {RippleModule} from "primeng/ripple";
import {ToastService} from "../shared/toast/ToastService";
@Component({
  standalone: true,
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
  imports: [
    SharedModule,
    RouterModule,
    CKEditorModule,
    ReactiveFormsModule,
    FormsModule,
    SafeHtmlPipe,
    OrderBySpecificKeyPipe,
    ToastModule,
    RippleModule,
    ButtonModule
  ],
  providers: [MessageService]
})
export default class HomeComponent implements OnInit, OnDestroy {
  title = 'angular';
  public Editor = eee.ClassicEditor;
  public config = {
    menuBar: {
      isVisible: true
    },
    toolbar: [
      'undo',
      'redo',
      '|',
      'previousPage',
      'nextPage',
      '|',
      'revisionHistory',
      'trackChanges',
      'comment',
      '|',
      'insertMergeField',
      'previewMergeFields',
      '|',
      'aiCommands',
      'aiAssistant',
      '|',
      'formatPainter',
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
      '|',
      'alignment',
      '|',
      'bulletedList',
      'numberedList',
      'multiLevelList',
      'todoList',
      'outdent',
      'indent',
    ],
    plugins: [
      eee.AccessibilityHelp,
      eee.Alignment,
      eee.Autoformat,
      eee.AutoImage,
      eee.AutoLink,
      eee.Autosave,
      eee.BalloonToolbar,
      eee.Bold,
      eee.CKBox,
      eee.CKBoxImageEdit,
      eee.CloudServices,
      eee.Code,
      eee.Essentials,
      eee.FindAndReplace,
      eee.FontBackgroundColor,
      eee.FontColor,
      eee.FontFamily,
      eee.FontSize,
      eee.Heading,
      eee.HorizontalLine,
      eee.ImageBlock,
      eee.ImageCaption,
      eee.ImageInline,
      eee.ImageInsert,
      eee.ImageInsertViaUrl,
      eee.ImageResize,
      eee.ImageStyle,
      eee.ImageTextAlternative,
      eee.ImageToolbar,
      eee.ImageUpload,
      eee.Indent,
      eee.IndentBlock,
      eee.Italic,
      eee.Link,
      eee.LinkImage,
      eee.List,
      eee.ListProperties,
      eee.Mention,
      eee.PageBreak,
      eee.Paragraph,
      eee.PasteFromOffice,
      eee.PictureEditing,
      eee.RemoveFormat,
      eee.SelectAll,
      eee.SpecialCharacters,
      eee.SpecialCharactersArrows,
      eee.SpecialCharactersCurrency,
      eee.SpecialCharactersEssentials,
      eee.SpecialCharactersLatin,
      eee.SpecialCharactersMathematical,
      eee.SpecialCharactersText,
      eee.Strikethrough,
      eee.Subscript,
      eee.Superscript,
      eee.Table,
      eee.TableCaption,
      eee.TableCellProperties,
      eee.TableColumnResize,
      eee.TableProperties,
      eee.TableToolbar,
      eee.TextTransformation,
      eee.TodoList,
      eee.Underline,
      eee.Undo,
    ],
    // licenseKey: '<YOUR_LICENSE_KEY>',
    // mention: {
    //     Mention configuration
    // }
  };
  keyword = '';
  account = signal<Account | null>(null);
  documentSelected: DocumentModel = new DocumentModel();
  contentChanged = '';
  searchResults: ElasticSearchItem<any>[] = [];
  isDisableSaveContent = true;

  private keywordSubject = new Subject<string>();
  private readonly destroy$ = new Subject<void>();
  private readonly accountService = inject(AccountService);
  private readonly router = inject(Router);

  constructor(
    private homeService: HomeService,
    private sanitizer: DomSanitizer,
    private messageService: MessageService
  ) {
  }

  ngOnInit(): void {
    this.messageService.add({severity: 'success', summary: 'Success', detail: 'Message Content'});
    this.accountService
      .getAuthenticationState()
      .pipe(takeUntil(this.destroy$))
      .subscribe(account => this.account.set(account));

    // Subscribe to keywordSubject with a debounce and API call
    this.keywordSubject.pipe(
      debounceTime(500), // wait for 500ms pause in input
      switchMap(keyword => this.homeService.getAll(keyword))
    ).subscribe({
      next: (result) => {
        this.searchResults = result.data;
        // console.dir(result.data)
        // this.documents = result.data; // Use arrow function
      },
      error: (error) => {
        console.dir(error); // Use arrow function
      },
    });
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }


  onKeywordChange(): void {
    // Emit new keyword to subject
    if (this.keyword && this.keyword.length >= 3) {
      this.keywordSubject.next(this.keyword);
    } else {
      this.searchResults = [];
      this.documentSelected = new DocumentModel();
    }
  }

  onLoadDocument(itemSelected: DocumentModel): void {
    if (itemSelected.content) {
      this.documentSelected = itemSelected;
      this.documentSelected.isEditing = false;
    }
  }

  transformHighlights(key: string, highlights: string[]): SafeHtml {
    const combinedHighlights = highlights.join(' ... ') + ' ... ';
    const formattedHighlights = key === 'title' ? `<b>${combinedHighlights}</b>` : combinedHighlights;
    return this.sanitizer.bypassSecurityTrustHtml(formattedHighlights);
  }

  onSaveData(): void {
    this.documentSelected.content = this.contentChanged;
    this.homeService.save(this.documentSelected).subscribe(
      {
        next: (result) => {
          window.alert('Success');
        },
        error: (error) => {
          console.dir(error); // Use arrow function
        },
      }
    )
  }

  onContentChange(value: string): void {
    this.contentChanged = value;
  }

  onEditData(): void {
    this.documentSelected = JSON.parse(JSON.stringify(this.documentSelected));
    this.documentSelected.isEditing = true
  }
}
