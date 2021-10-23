import { Occasion } from "../occasion/occasion";
import { Treatment } from "../treatment/treatment";

export class OccasionTreatment {
    id!: number;
    treatment!: Treatment;
    occasion!: Occasion;
}
