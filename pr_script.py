import os
from github_utils import get_pull_request_diff, post_review_comment
from llm_utils import analyze_code_changes

class PRreviewer:
    def __init__(self):
        self.openai_api_key = os.getenv('OPENAI_API_KEY')
        self.github_token = os.getenv('GITHUB_TOKEN')
        
    def review_pull_request(self):
        # Get the PR diff
        diff_content = get_pull_request_diff()
        
        # Analyze changes using LLM
        review_comments = analyze_code_changes(diff_content)
        
        # Post comments back to GitHub
        if review_comments:
            for comment in review_comments:
                post_review_comment(comment)

if __name__ == '__main__':
    reviewer = PRreviewer()
    reviewer.review_pull_request()
