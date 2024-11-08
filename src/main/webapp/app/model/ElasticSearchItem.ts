export class ElasticSearchItem<T> {
  id: string;
  source: T;
  score: number;
  highlights: Record<string, string[]>;

  constructor(id: string, source: T, score: number, highlights: Record<string, string[]>) {
    this.id = id;
    this.source = source;
    this.score = score;
    this.highlights = highlights;
  }
}
