export class Staff {
    staffId: number;
    firstName: string;
    lastName?: string;
    pesel?: string;
    roles?: Set<string>;
    orders?: Set<string>;
}
