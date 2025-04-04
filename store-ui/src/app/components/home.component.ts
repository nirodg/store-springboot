import { Component } from '@angular/core';
import { MatTableModule } from '@angular/material/table';
import { CommonModule } from '@angular/common';

interface Product {
  id: number;
  name: string;
  price: number;
  category: string;
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, MatTableModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  displayedColumns: string[] = ['id', 'name', 'price', 'category'];

  products: Product[] = [
    { id: 1, name: 'Blueberry Plant', price: 9.99, category: 'Fruits' },
    { id: 2, name: 'Garden Hose', price: 19.99, category: 'Tools' },
    { id: 3, name: 'Drip Irrigation Kit', price: 29.99, category: 'Irrigation' },
  ];
}
